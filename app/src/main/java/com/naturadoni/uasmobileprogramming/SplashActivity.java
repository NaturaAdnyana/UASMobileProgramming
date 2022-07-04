package com.naturadoni.uasmobileprogramming;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    final Handler handler = new Handler(Looper.getMainLooper());
    Intent nextIntent;
    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

    }

    @Override
    protected void onResume() {
        super.onResume();

        shared = getSharedPreferences("user_details", Context.MODE_PRIVATE);
        String username = shared.getString("username", "");

        if (!username.equals("")) {
            nextIntent = new Intent(SplashActivity.this, HomeActivity.class);
        } else {
            nextIntent = new Intent(SplashActivity.this, LoginActivity.class);
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(nextIntent);
            }
        }, 4000);
    }
}