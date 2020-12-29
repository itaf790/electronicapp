package com.app.electronicapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {


    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_splash);

        timer = new Timer();
        timer.schedule (new TimerTask (){
            @Override
            public void run() {
                Intent Intent = new Intent (Splash.this, home.class);
                startActivity (Intent);
                finish ();
            }
        }, 3000);
    }
}