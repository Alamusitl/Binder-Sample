package com.owl.binder;

import android.app.Application;
import android.os.Process;
import android.util.Log;

/**
 * Created by Alamusi on 2016/12/16.
 */

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: " + Process.myPid());
    }
}
