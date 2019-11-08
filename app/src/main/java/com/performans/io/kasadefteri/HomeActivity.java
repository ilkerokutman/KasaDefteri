package com.performans.io.kasadefteri;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeActivity  extends AppCompatActivity {

    //bu text alanını yukarda tanımlarsak her yerde kullanırız
    TextView gunlukGider;
    TextView gunlukGelir;
    TextView toplam;
    RecyclerView recyclerView;


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
         toplam = findViewById(R.id.txtToplam);


        recyclerView = findViewById(R.id.liste);

        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //veritabanı sadece bu onResume içerisinde lazım olduğu için burada tanımladık
        DatabaseHelper db = new DatabaseHelper(this);
        Log.d("aaaa", "bbbb");
        // yukarda tanımlanan textview içine burada yeni değer veriyoruz

        double giderToplam = db.getExpenseSum();
        Log.d("aaaa", "gider toplamı : " + giderToplam);
        String giderToplamMetni = "Günlük gider " + giderToplam + " TL";
        gunlukGider.setText(giderToplamMetni);

        Log.d("aaaa", "bbbb 1");
        double gelirToplam = db.getIncomeSum();
        String gelirToplamMetni = "Günlük gelir " + gelirToplam + " TL";
        gunlukGelir.setText(gelirToplamMetni);

        toplam.setText("Elde Kalan: " + String.valueOf(gelirToplam - giderToplam));

        //burada listeyi doldur
        Log.d("liste", "doldurulacak");
        listExpenses();

        Log.d("liste", "dolduruldu");
        Log.d("aaaa1", "bbbb 4");

    }

    // ikinci sayfayi acan fonksiyon
    public void openAddPage() {
        Intent intent = new Intent(HomeActivity.this, AddActivity.class);
        startActivity(intent);
    }

    public void listExpenses(){
        DatabaseHelper db = new DatabaseHelper(this);
        ArrayList<ExpenseModel> myList = db.getAllRecords();
        MyListAdapter adapter = new MyListAdapter(this, myList);
        recyclerView.setAdapter(adapter);
        Log.d("liste", "listede " + myList.size() + " kadar satır var");
    }
}
