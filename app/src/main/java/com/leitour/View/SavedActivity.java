package com.leitour.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
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
        ArrayList<Book> books = new ArrayList<>();


        Cursor cursor = databaseHelper.selectBooks();

          /*  while (cursor.moveToNext()) {
                Book book = new Book();
                book.setIsbn(cursor.getInt(0));
                book.setName(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                book.setPages(cursor.getInt(3));
                book.setEdition(cursor.getInt(4));
                book.setCover((byte) cursor.getInt(5));
                book.setSinopse(cursor.getString(6));
                book.setYear(cursor.getString(7));
                book.setLanguage(cursor.getString(8));
                books.add(book);
            }*/
        SavedAdapter savedAdapter = new SavedAdapter(getApplicationContext(),books);

        recyclerView.setAdapter(savedAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

       /*Book book = books.get(1);
        Log.d("isbn",String.valueOf(book.getIsbn()));
            Log.d("Nome",book.getName());
            Log.d("Autor",book.getAuthor());
            Log.d("Idioma",book.getLanguage());
            Log.d("Ano",book.getYear());
            Log.d("Sinopse",book.getSinopse());
            Log.d("cover",String.valueOf(book.getCover()));
            Log.d("Edition",String.valueOf(book.getEdition()));
            Log.d("Pages",String.valueOf(book.getPages()));*/
    }
}