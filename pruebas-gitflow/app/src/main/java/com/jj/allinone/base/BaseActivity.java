package com.jj.allinone.base;

import android.os.Bundle;
import butterknife.ButterKnife;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.jj.allinone.application.AllInOneApplication;
import com.jj.allinone.application.AllInOneComponent;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (fullScreen()) getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(getLayout());
        ButterKnife.bind(this);
        injectComponents(AllInOneApplication.getApp(this).getApplicationComponent());
        activityReady(savedInstanceState);
    }

    public boolean fullScreen(){
        return false;
    }

    protected abstract int getLayout();
    protected abstract void injectComponents(AllInOneComponent allInOneComponent);
    protected abstract void activityReady(Bundle savedInstanceState);
}
