package com.jj.allinone.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.jj.allinone.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AllInOneModule {

    private AllInOneApplication mApplication;

    public AllInOneModule(AllInOneApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return mApplication;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return mApplication;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application aplication) {
        return aplication.getSharedPreferences(Constants.APP_PREFS, Context.MODE_PRIVATE);
    }
}
