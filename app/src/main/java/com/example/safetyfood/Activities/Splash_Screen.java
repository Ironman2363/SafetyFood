package com.example.safetyfood.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import com.example.safetyfood.DAO.TaikhoanDAO;
import com.example.safetyfood.MODEL.TaiKhoan;
import com.example.safetyfood.R;

public class Splash_Screen extends AppCompatActivity {

    TaikhoanDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        CountDownTimer countDownTimer = new CountDownTimer(3000,3000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent( getApplicationContext(),Login.class ));
            }
        };
        countDownTimer.start();
    }

}