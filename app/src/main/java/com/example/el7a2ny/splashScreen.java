package com.example.el7a2ny;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class splashScreen extends AppCompatActivity {

    //@SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        EasySplashScreen config = new EasySplashScreen(splashScreen.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class) // edit here the next activity
                .withSplashTimeOut(500)
                .withBackgroundResource(R.color.colorPrimaryDark)
                .withLogo(R.drawable.logo_100);

        View easySplashScreenView = config.create();
        setContentView(easySplashScreenView);
    }
}
