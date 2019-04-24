package com.example.el7a2ny;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
                .withSplashTimeOut(1500)
                .withBackgroundResource(R.color.colorPrimaryDark)
                .withLogo(R.drawable.logo_100);

        Logic.symptomNames = getResources().getStringArray(R.array.symptoms_array_ar);
        Logic.conditionNames = getResources().getStringArray(R.array.conditions_array_ar);

        for (int i = 0; i < Logic.symptomNames.length; ++i){
            String v = Logic.symptomNames[i];
            if (!v.isEmpty()) Logic.symptomLinks.add(new Logic.Linker(v,i));
        }
        Collections.sort(Logic.symptomLinks,new Comparator<Logic.Linker>() {
            @Override
            public int compare(Logic.Linker obj1, Logic.Linker obj2) {
                return obj1.getStr().compareTo(obj2.getStr());
            }
        });

        for (Logic.Linker x : Logic.symptomLinks){
            Logic.symptomObjects.add(x.getStr());
        }

        View easySplashScreenView = config.create();

        int op = getSharedPreferences("userdetails", Context.MODE_PRIVATE).getInt("splash",1);
        if (op != 0)
            setContentView(easySplashScreenView);
        else{
            Intent t = new Intent(this, MainActivity.class);
            startActivity(t);
            finishActivity(0);
        }
        if (op == 2){
            MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.startnoise);
            mPlayer.start();
        }
    }
}
