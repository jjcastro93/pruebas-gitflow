package com.jj.allinone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.jcastro.cameraexample.R;
import com.jj.allinone.application.AllInOneComponent;
import com.jj.allinone.base.BaseActivity;

import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements HomeModelView {

    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void injectComponents(AllInOneComponent allInOneComponent) {

    }

    @Override
    protected void activityReady(Bundle savedInstanceState) {

    }

    @Override
    public boolean fullScreen() {
        return true;
    }

    @OnClick(R.id.tvGoCam)
    public void goCam(){
        startActivity(new Intent(HomeActivity.this, CameraActivity.class));
    }
}
