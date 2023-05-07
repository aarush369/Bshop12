package com.example.bshop1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.bshop1.Utils.FirebaseUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

new Handler().postDelayed(() -> {
    if(FirebaseUtils.isLoggedIn()){
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
    else {
        startActivity(new Intent(SplashActivity.this,PhoneLoginActivity.class));
        finish();
    }



},2000);






    }
}