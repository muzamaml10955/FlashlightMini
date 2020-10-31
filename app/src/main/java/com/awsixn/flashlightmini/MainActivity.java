package com.awsixn.flashlightmini;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
/*
*
* its publilish with zimtechjks with muzammal123 passowrd
* 
*
* */

public class MainActivity extends Activity {

    /*  Button onBtn,offBtn;*/
    Camera cam;
    Parameters p;
    boolean status;
    ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggleButton = findViewById(R.id.tb1);


        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!status) { //cam on
                        //  toggleButton.getBackground().setAlpha(45);
                        //  toggleButton.setBackgroundColor(Color.parseColor("#F4511E"));
                        cam = Camera.open();
                        p = cam.getParameters();
                        p.setFlashMode(Parameters.FLASH_MODE_TORCH);
                        cam.setParameters(p);
                        cam.startPreview();
                        status = true;
                    }
                    // The toggle is enabled
                } else {
//cam off
                    if (status) {
                        // toggleButton
                        //  toggleButton.getBackground().setAlpha(45);
                        // toggleButton.setBackgroundColor(Color.parseColor("#64DD17"));
                        cam.stopPreview();
                        cam.release();
                        status = false;
                    }
                    // The toggle is disabled
                }
            }
        });


    }
}