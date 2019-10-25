package com.performans.io.kasadefteri;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


    }

    // anasayfayi acan fonksiyon
    public void openHome() {
        Intent intentHome = new Intent(WelcomeActivity.this, HomeActivity.class);
        intentHome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentHome);
    }


    @Override
    protected void onResume() {
        // zamanlayici calistir
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // zaman doldugunda anasayfaya git
                openHome();
            }
        }, 2000);
        super.onResume();
    }
}
