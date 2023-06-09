package com.example.appleitour.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.appleitour.Model.Book;
import com.example.appleitour.R;

public class BookActivity extends AppCompatActivity {

  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        Book book = (Book) getIntent().getSerializableExtra("Book");
        TextView title, author, publisher, year;

        title = findViewById(R.id.book_title);
        author = findViewById(R.id.book_author);
        publisher = findViewById(R.id.book_publisher);
        year = findViewById(R.id.book_year);

        title.setText(book.getName());
        author.setText(book.getAuthor());
    }
}