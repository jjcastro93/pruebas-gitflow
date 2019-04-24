package com.jj.allinone.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CameraUtils {

    private static String TAG = CameraUtils.class.getSimpleName();

    public static boolean checkCameraHardware(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    private static int findFrontCamera() {
        int cameraId = -1;
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i;
                break;
            }
            cameraId = i;
        }
        return cameraId;
    }

    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(){
        Camera cam = null;
        try {
            cam = Camera.open(findFrontCamera()); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
            Log.d(TAG, "getCameraInstance: ");
        }
        return cam; // returns null if camera is unavailable
    }

    public static Camera.PictureCallback getPictureCallback(){
        return mPicture;
    }

    private static Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            File pictureFile = getOutputMediaFile();
            if (pictureFile == null){
                Log.d(TAG, "Error creating media file, check storage permissions");
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                Log.d(TAG, "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d(TAG, "Error accessing file: " + e.getMessage());
            }
        }
    };

    private static File getOutputMediaFile() {
        return new File(Environment.getExternalStorageDirectory() + "/" + getTimeStamp() + ".jpg");
    }

    private static String getTimeStamp(){
        Long tsLong = System.currentTimeMillis()/1000;
        return tsLong.toString();
    }

    public static Camera.Size getMaxPictureResolution(Camera camera) {
        Camera.Size currentMaxRes=null;
        List<Camera.Size> sizes = getPictureResolutionList(camera);
        for ( Camera.Size r: sizes) {
            if (r.width == 1920 && r.height == 1080){
                currentMaxRes = r;
            }
        }
        return currentMaxRes;
    }

    public static Camera.Size getMaxPictureResolution(float previewRatio, Camera camera) {
        int maxPixels=0;
        int ratioMaxPixels=0;
        Camera.Size currentMaxRes=null;
        Camera.Size ratioCurrentMaxRes=null;
        for ( Camera.Size r: CameraUtils.getPictureResolutionList(camera) ) {
            float pictureRatio = (float) r.width / r.height;
            int resolutionPixels = r.width * r.height;

            if (resolutionPixels>ratioMaxPixels && pictureRatio == previewRatio) {
                ratioMaxPixels=resolutionPixels;
                ratioCurrentMaxRes=r;
            }

            if (resolutionPixels>maxPixels) {
                maxPixels=resolutionPixels;
                currentMaxRes=r;
            }
        }

        if (ratioCurrentMaxRes!=null) {
            return ratioCurrentMaxRes;
        }

        return currentMaxRes;
    }

    public static List<Camera.Size> getPictureResolutionList(Camera camera) {
        return camera.getParameters().getSupportedPictureSizes();
    }

    public static int getRespectiveWidth(Camera.Size size, int height) {
        return (height*size.width)/size.height;
    }
}
