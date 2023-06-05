package com.leitour.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;

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
        ArrayList books = new ArrayList<>();


        Cursor cursor = databaseHelper.selectBooks();

            while (cursor.moveToNext()) {
                Book book = new Book();
                book.setIsbn(cursor.getInt(0));
                book.setName(cursor.getString(0));
                book.setAuthor(cursor.getString(0));
                book.setCover((byte) cursor.getInt(0));
                book.setEdition(cursor.getInt(0));
                book.setPages(cursor.getInt(0));
                book.setSinopse(cursor.getString(0));
                book.setLanguage(cursor.getString(0));
                book.setYear(cursor.getString(0));

                books.add(book);
            }
/*        SavedAdapter savedAdapter = new SavedAdapter( (Activity) getApplicationContext(), getApplicationContext(),books);
        recyclerView.setAdapter(savedAdapter);*/
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}