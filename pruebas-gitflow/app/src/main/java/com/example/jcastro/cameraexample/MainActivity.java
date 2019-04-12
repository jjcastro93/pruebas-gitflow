package com.example.jcastro.cameraexample;

import android.content.Intent;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private Camera mCamera;
    private CameraPreview mPreview;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Button captureButton = findViewById(R.id.button_capture);
        frameLayout = findViewById(R.id.camera_preview);
        captureButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // get an image from the camera
                        //mCamera.takePicture(null, null, CameraUtils.getPictureCallback());
                        /*int height = frameLayout.getHeight();
                        Camera.Size size = CameraUtils.getMaxPictureResolution(mCamera);

                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mPreview.getLayoutParams();
                        layoutParams.width = CameraUtils.getRespectiveWidth(size, height);
                        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
                        mPreview.setLayoutParams(layoutParams);*/

                        startActivity(new Intent(MainActivity.this,TransparentActivityActivity.class));
                    }
                }
        );
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void openCamera() {
        if (CameraUtils.checkCameraHardware(this)) {
            mCamera = CameraUtils.getCameraInstance();

            // Create our Preview view and set it as the content of our activity.
            mPreview = new CameraPreview(this, mCamera);
            FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
            preview.addView(mPreview);
        }
    }


}
