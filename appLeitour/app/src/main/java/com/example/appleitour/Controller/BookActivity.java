package com.example.appleitour.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appleitour.Adapter.AnnotationAdapter;
import com.example.appleitour.Database.DatabaseHelper;
import com.example.appleitour.Model.Annotation;
import com.example.appleitour.Model.Book;
import com.example.appleitour.Model.User;
import com.example.appleitour.Model.UserBook;
import com.example.appleitour.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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
        ImageView backgroundCover = findViewById(R.id.book_background);
        ImageView bookCover = findViewById(R.id.book_cover);
        TextView title = findViewById(R.id.book_title);
        TextView author = findViewById(R.id.book_author);
        TextView publisher = findViewById(R.id.book_publisher);
        TextView year = findViewById(R.id.book_year);
        TextView sinopse = findViewById(R.id.txt_book_sinopse);

        Picasso.get().load(book.getCover()).into(backgroundCover);
        Picasso.get().load(book.getCover()).into(bookCover);
        title.setText(book.getName());
        author.setText(book.getAuthor());
        publisher.setText(book.getPublisher());
        year.setText(book.getYear());
        sinopse.setText(book.getSinopse());

        RecyclerView recyclerView = findViewById(R.id.recycler_annotation);
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        SharedPreferences settings = getSharedPreferences("com.example.appleitour", 0);
        int userId = settings.getInt("UserId", 0);
        if(!db.checkUserBook(book.getKey(), userId)) {
            btnCreate.setVisibility(View.VISIBLE);
            int userBookId = db.selectLastInsertBookId(book.getKey(), userId);
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
            }
            else if(db.checkUserBook(book.getKey(), userId)){// Se ele ta no banco, mas não salvei ele, salva pra mim
                db.insertUserBook(book.getKey(),userId);
            }
            else { // Se ele esta no banco e eu salvei ele, cliquei denovo porque quero apagar
                db.deleteUserBook(book.getKey(),userId);
            }
            if(db.checkBookIsSaved(book.getKey())){ // Se o livro nao tiver sido salvo por nenhum usuario, apaga do banco
                db.deleteBook(book.getKey());
            }
        });
    }
}