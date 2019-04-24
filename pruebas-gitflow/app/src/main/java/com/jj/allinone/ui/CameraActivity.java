package com.jj.allinone.ui;

import android.hardware.Camera;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.jj.allinone.application.AllInOneComponent;
import com.jj.allinone.utils.CameraUtils;
import com.example.jcastro.cameraexample.R;
import com.jj.allinone.base.BaseActivity;
import com.jj.allinone.custom.CameraPreview;

import butterknife.BindView;
import butterknife.OnClick;

public class CameraActivity extends BaseActivity {

    @BindView(R.id.camera_preview) FrameLayout frameLayout;

    private Camera mCamera;
    private CameraPreview mPreview;

    @OnClick(R.id.button_capture)
    public void onCaptureClick() {
        // get an image from the camera
        //mCamera.takePicture(null, null, CameraUtils.getPictureCallback());
        int height = frameLayout.getHeight();
        Camera.Size size = CameraUtils.getMaxPictureResolution(mCamera);

        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mPreview.getLayoutParams();
        layoutParams.width = CameraUtils.getRespectiveWidth(size, height);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        mPreview.setLayoutParams(layoutParams);

        //startActivity(new Intent(CameraActivity.this,TransparentActivityActivity.class));
    }

    @Override
    public boolean fullScreen() {
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        openCamera();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCamera.release();
    }

    private void openCamera() {
        if (CameraUtils.checkCameraHardware(this)) {
            mCamera = CameraUtils.getCameraInstance();

            // Create our Preview view and set it as the content of our activity.
            mPreview = new CameraPreview(this, mCamera);
            frameLayout.addView(mPreview);
        }
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void injectComponents(AllInOneComponent allInOneComponent) {

    }

    @Override
    protected void activityReady(Bundle savedInstanceState) {

    }
}
