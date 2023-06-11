package com.example.appleitour.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appleitour.Adapter.AnnotationAdapter;
import com.example.appleitour.Database.DatabaseHelper;
import com.example.appleitour.Model.Annotation;
import com.example.appleitour.Model.Book;
import com.example.appleitour.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

public class BookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        FloatingActionButton btnSave = findViewById(R.id.btn_save_book);
        FloatingActionButton btnCreate = findViewById(R.id.btn_create_annotation);
        btnCreate.setVisibility(View.INVISIBLE);

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        db.getReadableDatabase();
        Book book = (Book) getIntent().getSerializableExtra("Book");
        TextView title = findViewById(R.id.book_title);
        TextView author = findViewById(R.id.book_author);
        TextView publisher = findViewById(R.id.book_publisher);
        TextView year = findViewById(R.id.book_year);
        TextView sinopse = findViewById(R.id.txt_book_sinopse);
        TextView isbn = findViewById(R.id.book_isbn);
        TextView edition = findViewById(R.id.book_edition);
        TextView language = findViewById(R.id.book_language);
        TextView pages = findViewById(R.id.book_pages);

        ImageView bookCover = findViewById(R.id.book_cover);
        ImageView bookBackground = findViewById(R.id.book_background);
        title.setText(getResources().getString(R.string.name,book.getName()));
        author.setText(getResources().getString(R.string.author,book.getAuthor()));
        publisher.setText(getResources().getString(R.string.publisher,book.getPublisher()));
        year.setText(getResources().getString(R.string.year,book.getYear()));
       /* if(!Objects.equals(book.getSinopse(), "-"))
            sinopse.setText(getResources().getString(R.string.sinopse,book.getSinopse()));*/
        if(book.getIsbn() != 0)
            isbn.setText(getResources().getString(R.string.isbn,book.getIsbn()));
        if(book.getEdition() != 0)
            edition.setText(getResources().getString(R.string.edition,book.getEdition()));
        if(!Objects.equals(book.getLanguage(), "-"))
            language.setText(getResources().getString(R.string.language,book.getLanguage()));
        if(book.getPages() != 0)
            pages.setText(getResources().getString(R.string.pages,book.getPages()));
        Picasso.get().load(book.getCover()).into(bookCover);
        Picasso.get().load(book.getCover()).into(bookBackground);

        RecyclerView recyclerView = findViewById(R.id.recycler_annotation);
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        SharedPreferences settings = getSharedPreferences("com.example.appleitour", 0);
        int userId = settings.getInt("UserId", 0);
        if(!db.checkUserBook(book.getKey(), userId)) {
            btnCreate.setVisibility(View.VISIBLE);
            int userBookId = db.selectLastInsertBookId(book.getKey(), userId);
            btnSave.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.saved));
            ArrayList<Annotation> annotation = databaseHelper.selectAnnotations(userBookId);
            AnnotationAdapter annotationAdapter = new AnnotationAdapter(this,annotation,book);
            recyclerView.setAdapter(annotationAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        btnCreate.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AnnotationActivity.class);
            intent.putExtra("Book",book);
            int userBookId = db.selectLastInsertBookId(book.getKey(), userId);
            intent.putExtra("UserBook",userBookId);
            startActivity(intent);
            finish();
        });

        btnSave.setOnClickListener(view -> {
            if(db.checkBookIsStored(book.getKey())) {// Se o livro nao estiver no banco, salva
                db.insertBook(book);
                db.insertUserBook(book.getKey(),userId);
                btnSave.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.saved));
            }
            else if(db.checkUserBook(book.getKey(), userId)){// Se ele ta no banco, mas não salvei ele, salva pra mim
                db.insertUserBook(book.getKey(),userId);
                btnSave.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.saved));
            }
            else { // Se ele esta no banco e eu salvei ele, cliquei denovo porque quero apagar
                db.deleteUserBook(book.getKey(),userId);
                btnSave.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tosave));
            }
            if(db.checkBookIsSaved(book.getKey())){ // Se o livro nao tiver sido salvo por nenhum usuario, apaga do banco
                db.deleteBook(book.getKey());
            }
        });
    }
}