package com.jj.allinone.application;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AllInOneModule.class)
public interface AllInOneComponent {
    Context getContext();
    SharedPreferences getSharedPreferences();
}
