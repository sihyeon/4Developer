package com.project4D.fdpay;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.project4D.fdpay.view.Drawer;


/**
 * 1. extends AppCompatAcitivity
 * 2. getSupportActionBar().setDisplayHomeAsUpEnabled(true);
 * getSupportActionBar().setHomeButtonEnabled(false);
 * 3. onOptionsItemSelected(MenuItem item)
 *
 * @author Somin Lee(sayyo1120@gmail.com)
 * @version 10.1 (2015-08-10)
 * @see this Activity is the main Activity - card in this app, and I am fucking crazy to make.
 */

public class CardActivity extends ActionBarActivity {
    private Drawer drawer;
    private Fragment pointCardFrag, creditCardFrag;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        drawer = Drawer.newInstance(this);
        pointCardFrag = new PointCardFragment().offerDrawer(drawer, Drawer.POINT_CARD_POSITION);
        creditCardFrag = new CreditCardFragment().offerDrawer(drawer, Drawer.CREDIT_CARD_POSITION);

        transactFragment(creditCardFrag);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        IntentFilter i = new IntentFilter();
        registerReceiver(
                broadcastReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        String f = intent.getStringExtra("fragment");
                        if (f == null) return;
                        if (f.equals(pointCardFrag.getClass().getName()))
                            transactFragment(pointCardFrag);
                        else
                            transactFragment(creditCardFrag);
                    }
                }, i);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

    private void transactFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.card_fragment, fragment).commit();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Handle drawer
            case android.R.id.home:
                if (drawer.isDrawerOpen())
                    drawer.closeDrawer();
                else
                    drawer.openDrawer();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private long backKeyPressedTime = 0;

    @Override
    public void onBackPressed() {
        // Handle Drawer
        if (drawer != null && drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        }
        // Prevent one-back activity finishing
        else {
            // one-back
            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis();
                Toast.makeText(
                        CardActivity.this,
                        "한번 더 뒤로가기 버튼을 누르시면 앱이 종료됩니다.",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            // two-back
            else {
                super.onBackPressed();
            }
        }
    }


    public void setActionBarTitle(String myname) {
        setActionBarTitle(myname);
    }
}
