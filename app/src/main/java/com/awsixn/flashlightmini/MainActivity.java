package com.awsixn.flashlightmini;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import  com.facebook.ads.*;
/*
 *
 * its publilish with zimtechjks with muzammal123 passowrd
 *
 *
 * */


public class MainActivity extends Activity {

    private CameraManager mCameraManager;
    private String mCameraId;
    /*  Button onBtn,offBtn;*/
    Camera camera;
    Parameters parameater;
    boolean flag;
    ToggleButton onoffbutton;
    private AdView adView,adviewtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onoffbutton = findViewById(R.id.tb1);
        Context context = this;



        adView = new AdView(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID", AdSize.RECTANGLE_HEIGHT_250);

        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);

        // Add the ad view to your activity layout
        adContainer.addView(adView);

        // Request an ad
        adView.loadAd();




        adviewtop = new AdView(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID", AdSize.RECTANGLE_HEIGHT_250);

        // Find the Ad Container
        LinearLayout adContainertop = (LinearLayout) findViewById(R.id.banner_containertop);

        // Add the ad view to your activity layout
        adContainertop.addView(adviewtop);

        // Request an ad
        adviewtop.loadAd();





// Initialize the Audience Network SDK
        AudienceNetworkAds.initialize(this);



        boolean isFlashAvailable = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!isFlashAvailable) {
            showNoFlashError();
        }



        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 200);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            // Do something for lollipop and above versions
            highverionlight(isFlashAvailable);


            //   lightonlowverion();

        } else {


            lightonlowverion();


            // do something for phones running an SDK before lollipop
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void highverionlight(boolean isFlashAvailable) {
        //getting the camera manager and camera id
        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            mCameraId = mCameraManager.getCameraIdList()[0];
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (!isFlashAvailable) {
            showNoFlashError();
        }

        //getting the camera manager and camera id
        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            mCameraId = mCameraManager.getCameraIdList()[0];
        } catch (Exception e) {
            e.printStackTrace();
        }


        onoffbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //called when the button status is changed
                switchFlashLight(isChecked);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void switchFlashLight(boolean status) {
        try {
            mCameraManager.setTorchMode(mCameraId, status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void showNoFlashError() {
        AlertDialog alert = new AlertDialog.Builder(this)
                .create();
        alert.setTitle("Oops!");
        alert.setMessage("Flash not available in this device...");
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alert.show();
    }

    private void lightonlowverion() {
        onoffbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    try {
                        //code here File IO operation like Files
                        // Code here Network Operation
                        //code here /0 (Divide by Zero )
                        if (!flag) { //cam on
                            //  toggleButton.getBackground().setAlpha(45);
                            //  toggleButton.setBackgroundColor(Color.parseColor("#F4511E"));
                            camera = Camera.open();
                            parameater = camera.getParameters();
                            parameater.setFlashMode(Parameters.FLASH_MODE_TORCH);
                            camera.setParameters(parameater);
                            camera.startPreview();
                            flag = true;

                        }
                    } catch (Exception e) {
                        Log.e("Fail 2", e.toString());
                        //At the level Exception Class handle the error in Exception Table
                        // Exception Create That Error  Object and throw it
                        //E.g: FileNotFoundException ,etc
                        e.printStackTrace();
                    }

                }
                // The toggle is enabled
                else {
//cam off
                    if (flag) {
                        // toggleButton
                        //  toggleButton.getBackground().setAlpha(45);
                        // toggleButton.setBackgroundColor(Color.parseColor("#64DD17"));
                        camera.stopPreview();
                        camera.release();
                        flag = false;
                    }
                    // The toggle is disabled
                }
            }
        });






    }


    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();

        if (adviewtop != null) {
            adviewtop.destroy();
        }
        super.onDestroy();

    }

}