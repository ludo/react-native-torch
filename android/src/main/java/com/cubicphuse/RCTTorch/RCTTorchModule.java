/**
 * Created by Ludo van den Boom <ludo@cubicphuse.nl> on 06/04/2017.
 */

package com.cubicphuse.RCTTorch;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.content.pm.PackageManager;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RCTTorchModule extends ReactContextBaseJavaModule {
    private final ReactApplicationContext myReactContext;
    public boolean isTorchOn = false;
    protected Camera camera;
    protected Parameters params;

    public RCTTorchModule(ReactApplicationContext reactContext) {
        super(reactContext);

        // Need access to reactContext to check for camera
        this.myReactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RCTTorch";
    }

    @ReactMethod
    public void switchState(Boolean newState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CameraManager cameraManager =
                    (CameraManager) this.myReactContext.getSystemService(Context.CAMERA_SERVICE);

            try {
                String cameraId = cameraManager.getCameraIdList()[0];
                cameraManager.setTorchMode(cameraId, newState);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
        else {
            if (!isTorchOn) {
                camera = Camera.open();
                params = camera.getParameters();

                params.setFlashMode(Parameters.FLASH_MODE_TORCH);
                camera.setParameters(params);
                camera.startPreview();
                isTorchOn = true;
            }
            else {
                params = camera.getParameters();
                params.setFlashMode(Parameters.FLASH_MODE_OFF);

                camera.setParameters(params);
                camera.stopPreview();
                camera.release();
                isTorchOn = false;
            }
        }
    }
}

