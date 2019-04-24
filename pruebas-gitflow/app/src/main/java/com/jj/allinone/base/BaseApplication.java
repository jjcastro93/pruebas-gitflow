package com.jj.allinone.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.example.jcastro.cameraexample.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class BaseApplication extends Application {

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //MultiDex.install(this);
    }
}
