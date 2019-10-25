package com.performans.io.kasadefteri;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    EditText input_desc;
    EditText input_amount;
    Button addInc;
    Button addExp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // viewdaki nesneleri sayfaya tanitalim.
        input_desc = findViewById(R.id.iDescription);
        input_amount = findViewById(R.id.iAmount);
        addInc = findViewById(R.id.btnAddIncome);
        addExp = findViewById(R.id.btnAddExpense);

        //ekleme butonunu dinleyelim
        addInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem(false);
            }
        });

        //ekleme butonunu dinleyelim
        addExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem(true);
            }
        });
    }


    public void addItem(boolean isExpense) {
        //aciklama metnini al
        String description = input_desc.getText().toString();

        // miktari al ve rakama cevirmeye calis
        String amountString = input_amount.getText().toString();
        if (amountString.equals("")) {
            amountString = "0";
        }
        double amount;
        try {
            // double yapmaya calis
            amount = Double.parseDouble(amountString);
        } catch (Exception e) {
            // yapamazsa sifir olsun
            amount = 0;
        }


        // ekrana yazdir
        Toast.makeText(this, description + "\n" + String.valueOf(amount) + "\n" + (isExpense ? "gider" : "gelir"), Toast.LENGTH_SHORT).show();

    }


}
