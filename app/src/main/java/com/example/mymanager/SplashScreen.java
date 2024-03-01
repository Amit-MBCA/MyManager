package com.example.mymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
//        getSupportActionBar().hide();
        Thread thread=new Thread(){
            public void run() {
                try {
                    sleep(1500);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent main=new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(main);
                    finish();
                }
            }
        };
        thread.start();
    }
}