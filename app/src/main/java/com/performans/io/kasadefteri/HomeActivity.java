package com.performans.io.kasadefteri;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HomeActivity  extends AppCompatActivity {

    //bu text alanını yukarda tanımlarsak her yerde kullanırız
    TextView gunlukGider;
    TextView gunlukGelir;
    ListView listView;


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

        // yukardad tanımlanan textview i sayfaya çağırıyoruz
         gunlukGider = findViewById(R.id.txtGunlukGider);
         gunlukGelir = findViewById(R.id.txtGunlukGelir);
         listView = findViewById(R.id.liste);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //veritabanı sadece bu onResume içerisinde lazım olduğu için burada tanımladık
        DatabaseHelper db = new DatabaseHelper(this);

        // yukarda tanımlanan textview içine burada yeni değer veriyoruz

        double giderToplam = db.getExpenseSum();
        String giderToplamMetni = "Günlük gider " + giderToplam + " TL";
        gunlukGider.setText(giderToplamMetni);

        double gelirToplam = db.getIncomeSum();
        String gelirToplamMetni = "Günlük gelir " + gelirToplam + " TL";
        gunlukGelir.setText(gelirToplamMetni);



        //burada listeyi doldur

    }

    // ikinci sayfayi acan fonksiyon
    public void openAddPage() {
        Intent intent = new Intent(HomeActivity.this, AddActivity.class);
        startActivity(intent);
    }

    public void listExpenses(){
        DatabaseHelper db = new DatabaseHelper(this);
        ArrayList<ExpenseModel> myList = db.getAllRecords();


    }
}
