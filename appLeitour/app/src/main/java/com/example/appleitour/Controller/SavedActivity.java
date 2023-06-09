package com.example.appleitour.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.appleitour.Adapter.SavedAdapter;
import com.example.appleitour.Database.DatabaseHelper;
import com.example.appleitour.Model.Book;
import com.example.appleitour.R;

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