package com.leitour.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.leitour.Database.DatabaseHelper;
import com.leitour.R;

import java.util.ArrayList;

public class SavedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

    /*    DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        _id = new ArrayList < > ();
        _nome = new ArrayList < > ();
        _email = new ArrayList < > ();
        _mensagem = new ArrayList< >();

        criarArraydeDados();
        SavedAdapter savedAdapter = new SavedAdapter(getApplicationContext(), );
        recyclerView.setAdapter(SavedAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));*/
    }
}