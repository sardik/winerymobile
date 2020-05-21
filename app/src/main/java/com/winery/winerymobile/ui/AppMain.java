package com.winery.winerymobile.ui;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.multidex.BuildConfig;
import androidx.multidex.MultiDexApplication;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class AppMain extends MultiDexApplication {

    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context) {
        AppMain application = (AppMain) context.getApplicationContext();
        return application.refWatcher;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG)
            refWatcher = LeakCanary.install(this);
        Log.d("installLeak", "onCreate: masuk");

    }
}
