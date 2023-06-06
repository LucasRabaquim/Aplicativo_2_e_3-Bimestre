package com.leitour.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.leitour.Database.DatabaseHelper;
import com.leitour.Model.Book;
import com.leitour.R;

import java.util.ArrayList;

public class SavedActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        recyclerView = findViewById(R.id.recycler_saved_book);
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        ArrayList<Book> books = databaseHelper.selectBooks();
        SavedAdapter savedAdapter = new SavedAdapter(this,books);
        recyclerView.setAdapter(savedAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}