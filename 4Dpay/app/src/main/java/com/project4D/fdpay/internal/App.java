package com.project4D.fdpay.internal;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.project4D.fdpay.accessory.ContentsProvider;

/**
 * @author Somin Lee (sayyo1120@gmail.com)
 * @version 2015-09-30.
 * Service starts when application starts
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent(this, ContentsProvider.class);
        getApplicationContext().startService(intent);
    }
}
