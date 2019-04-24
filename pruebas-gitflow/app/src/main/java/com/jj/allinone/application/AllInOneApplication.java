package com.jj.allinone.application;

import android.content.Context;

import com.jj.allinone.base.BaseApplication;

public class AllInOneApplication extends BaseApplication {
    AllInOneComponent mAllInOneComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setUpApplicationComponent();
    }

    public static AllInOneApplication getApp(Context context){
        return ((AllInOneApplication)context.getApplicationContext());
    }

    private void setUpApplicationComponent(){
        mAllInOneComponent = DaggerAllInOneComponent.builder()
                .allInOneModule(new AllInOneModule(this))
                .build();
    }

    public AllInOneComponent getApplicationComponent(){
        if (mAllInOneComponent == null) setUpApplicationComponent();

        return mAllInOneComponent;
    }
}
