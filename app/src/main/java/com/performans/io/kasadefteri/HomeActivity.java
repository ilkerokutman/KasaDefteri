package com.performans.io.kasadefteri;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //EKLE butonunu tanimlama
        Button btnAdd = findViewById(R.id.btnAdd);

        //ekle butonuna tiklandiginda ne olsun
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // tiklandiginda 2. sayfaya git
                openAddPage();
            }
        });
    }


    // ikinci sayfayi acan fonksiyon
    public void openAddPage() {
        Intent intent = new Intent(HomeActivity.this, AddActivity.class);
        startActivity(intent);
    }
}
