package com.leitour.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.TextView;

import com.leitour.Database.DatabaseHelper;
import com.leitour.Model.Book;
import com.leitour.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(view -> {
            TextView texto = findViewById(R.id.placeholder);
            Book livro = new Book(123,"oi","Zezinho",41,1, (byte) 156,"Bando de burro","Port","2022");
            DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
            dbHelper.insertBook(livro);


            texto.setText("Opa");
        });
    }




}