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
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import  com.facebook.ads.*;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Arrays;
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
    private InterstitialAd interstitialAd;
    String TAG="tag";
  //  Button add;


    // admob init
    private com.google.android.gms.ads.AdView adViewadmob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onoffbutton = findViewById(R.id.tb1);
        Context context = this;
loadad();
/*add=findViewById(R.id.add);
add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});*/





        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });

        // Set your test devices. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("ABCDEF012345"))
        // to get test ads on this device."
        MobileAds.setRequestConfiguration(
                new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("f108046a-9e15-43de-86cf-7f03ac7bd540"))
                        .build());

        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        adViewadmob = findViewById(R.id.adView_banner_admob);

        // Create an ad request.
        com.google.android.gms.ads.AdRequest adRequestadmob = new AdRequest.Builder().build();

        // Start loading the ad in the background.
        adViewadmob.loadAd(new AdRequest.Builder().build());
//admob ad end here











   /*     adView = new AdView(this, getString(R.string.fb_mediumRectr_ad_id), AdSize.RECTANGLE_HEIGHT_250);

        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);

        // Add the ad view to your activity layout
        adContainer.addView(adView);

        // Request an ad
        adView.loadAd();*/




        adviewtop = new AdView(this, getString(R.string.fb_mediumRectr_ad_id), AdSize.RECTANGLE_HEIGHT_250);

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


    private void loadad() {


        // interstitialAd = new InterstitialAd(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID");  //test

        interstitialAd = new InterstitialAd(this, getString(R.string.fb_int_id));
        // Create listeners for the Interstitial Ad
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e(TAG, "Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d(TAG, "Interstitial ad impression logged!");
            }
        };

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());
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