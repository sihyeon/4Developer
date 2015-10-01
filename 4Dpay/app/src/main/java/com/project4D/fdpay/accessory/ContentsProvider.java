package com.project4D.fdpay.accessory;

import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.project4D.fdpay.manager.CreditCardTableManager;
import com.project4D.fdpay.manager.PointCardTableManager;
import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.accessory.SA;
import com.samsung.android.sdk.accessory.SAAgent;
import com.samsung.android.sdk.accessory.SAAuthenticationToken;
import com.samsung.android.sdk.accessory.SAPeerAgent;
import com.samsung.android.sdk.accessory.SASocket;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * @author Somin Lee(sayyo1120@gmail.com)
 * @version 2015-09-28.
 */
public class ContentsProvider extends SAAgent {
    private CreditCardTableManager cm = new CreditCardTableManager(this);
    private PointCardTableManager pm = new PointCardTableManager(this);
    private static final int CHANNEL_ID = 104;
    private static final String TAG = "Contents(P)";
    private static final Class<ServiceConnection> SASOCKET_CLASS = ServiceConnection.class;
    private final IBinder mBinder = new LocalBinder();
    private ServiceConnection mConnectionHandler = null;
    Handler mHandler = new Handler();

    public ContentsProvider() {
        super(TAG, SASOCKET_CLASS);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SA sa = new SA();
        try {
            sa.initialize(this);
        } catch (SsdkUnsupportedException e) {
            if (processUnsupportedException(e) == true) {
                return;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            /*
             * Your application can not use Samsung Accessory SDK. Your application should work smoothly
             * without using this SDK, or you may want to notify user and close your application gracefully
             * (release resources, stop Service threads, close UI thread, etc.)
             */
            stopSelf();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    protected void onFindPeerAgentResponse(SAPeerAgent peerAgent, int result) {
        // To notify pear Agent found
        Log.d(TAG, "onFindPeerAgentResponse : result =" + result);
        if(result == PEER_AGENT_FOUND)
            requestServiceConnection(peerAgent);
    }

    @Override
    protected void onServiceConnectionRequested(SAPeerAgent peerAgent) {
        // This function called when requestServiceConnection called.
        // for specify the value
        if (peerAgent != null) {
            Log.d(TAG, "onServiceConnectionRequested ");
            acceptServiceConnectionRequest(peerAgent);
        }
    }

    @Override
    protected void onServiceConnectionResponse(SAPeerAgent peerAgent, SASocket socket, int result) {
        if (result == SAAgent.CONNECTION_SUCCESS) {
            if (socket != null) {
                mConnectionHandler = (ServiceConnection) socket;
            }
        } else if (result == SAAgent.CONNECTION_ALREADY_EXIST) {
            Log.e(TAG, "onServiceConnectionResponse, CONNECTION_ALREADY_EXIST");
        } else if (result == CONNECTION_FAILURE_NETWORK) {
            try {
                wait(1000);
                requestServiceConnection(peerAgent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void onAuthenticationResponse(SAPeerAgent peerAgent, SAAuthenticationToken authToken, int error) {
        /*
         * The authenticatePeerAgent(peerAgent) API may not be working properly depending on the firmware
         * version of accessory device. Please refer to another sample application for Security.
         */
    }

    @Override
    protected void onError(SAPeerAgent peerAgent, String errorMessage, int errorCode) {
        super.onError(peerAgent, errorMessage, errorCode);
    }

    private boolean processUnsupportedException(SsdkUnsupportedException e) {
        e.printStackTrace();
        int errType = e.getType();
        if (errType == SsdkUnsupportedException.VENDOR_NOT_SUPPORTED
                || errType == SsdkUnsupportedException.DEVICE_NOT_SUPPORTED) {
            /*
             * Your application can not use Samsung Accessory SDK. You application should work smoothly
             * without using this SDK, or you may want to notify user and close your app gracefully (release
             * resources, stop Service threads, close UI thread, etc.)
             */
            stopSelf();
        } else if (errType == SsdkUnsupportedException.LIBRARY_NOT_INSTALLED) {
            Log.e(TAG, "You need to install Samsung Accessory SDK to use this application.");
        } else if (errType == SsdkUnsupportedException.LIBRARY_UPDATE_IS_REQUIRED) {
            Log.e(TAG, "You need to update Samsung Accessory SDK to use this application.");
        } else if (errType == SsdkUnsupportedException.LIBRARY_UPDATE_IS_RECOMMENDED) {
            Log.e(TAG, "We recommend that you update your Samsung Accessory SDK before using this application.");
            return false;
        }
        return true;
    }

    public class LocalBinder extends Binder {
        public ContentsProvider getService() {
            return ContentsProvider.this;
        }
    }

    public class ServiceConnection extends SASocket {
        private JSONObject card = null;

        public ServiceConnection() {
            super(ServiceConnection.class.getName());
        }

        @Override
        public void onError(int channelId, String errorMessage, int errorCode) {}

        @Override
        public void onReceive(int channelId, byte[] data) {
            if (mConnectionHandler == null) {return;}

            if (data.toString().equals("ALL")) {
                try {
                    card.put("CREDIT", cm.getAll());
                    card.put("POINT", pm.getAll());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            final String message;
            try {
                message = card.getString("CREDIT");
                message.concat("POINT CARD : ");
                message.concat(card.getString("POINT"));
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            mConnectionHandler.secureSend(CHANNEL_ID, message.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onServiceConnectionLost(int reason) {
            mConnectionHandler = null;
            mHandler.post(new Runnable() {
                @Override
                public void run() {

                }
            });
        }
    }
}
