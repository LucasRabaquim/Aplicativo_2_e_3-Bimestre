package com.example.appleitour.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.appleitour.Adapter.AnnotationAdapter;
import com.example.appleitour.Database.DatabaseHelper;
import com.example.appleitour.Model.Annotation;
import com.example.appleitour.Model.Book;
import com.example.appleitour.Model.User;
import com.example.appleitour.Model.UserBook;
import com.example.appleitour.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        FloatingActionButton btnSave = findViewById(R.id.btn_save_book);
        FloatingActionButton btnCreate = findViewById(R.id.btn_create_annotation);
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        db.getReadableDatabase();
        Book book = (Book) getIntent().getSerializableExtra("Book");
        TextView title = findViewById(R.id.book_title);
        TextView author = findViewById(R.id.book_author);
        TextView publisher = findViewById(R.id.book_publisher);
        TextView year = findViewById(R.id.book_year);
        TextView sinopse = findViewById(R.id.txt_book_sinopse);

        title.setText(book.getName());
        author.setText(book.getAuthor());
        publisher.setText(book.getPublisher());
        year.setText(book.getYear());
        sinopse.setText(book.getSinopse());

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        User user = new User("Lucas","123","email",(byte)123,123);
        dbHelper.insertUser(user);


        RecyclerView recyclerView = findViewById(R.id.recycler_annotation);
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        UserBook userBook = new UserBook(book.getKey(), 1);
        ArrayList<Annotation> annotation = databaseHelper.selectAnnotations(userBook);
        AnnotationAdapter annotationAdapter = new AnnotationAdapter(this,annotation);
        recyclerView.setAdapter(annotationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        btnCreate.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AnnotationActivity.class);
            intent.putExtra("Book",book);
            startActivity(intent);
        });

        btnSave.setOnClickListener(view -> {
            SharedPreferences settings = getSharedPreferences("com.example.appleitour", 0);
            int userId = 1;//settings.getInt("userId", 1);
            Log.d("Livros",String.valueOf(db.selectBooks()));
            Log.d("Livros Stored",String.valueOf(db.checkBookIsStored(book.getKey())));
            Log.d("Livros Salvo",String.valueOf(db.checkBookIsSaved(book.getKey())));
            Log.d("Livros USERBOOK",String.valueOf(db.checkUserBook(book.getKey(),userId)));

            if(db.checkBookIsStored(book.getKey())) {
                db.insertBook(book);
                Log.d("BANCO","Livro inserido");
            }else{
                db.deleteBook(book.getKey());
                Log.d("BANCO", "Livro deletado");
            }
            if(db.checkUserBook(book.getKey(), userId)){
                db.insertUserBook(book.getKey(),userId);
                Log.d("BANCO","Relação criada");
            }
          else {
                db.deleteUserBook(book.getKey(),userId);
                Log.d("BANCO","Relação deletada");
               /* if(!db.checkBookIsSaved(book.getKey())) {
                    db.deleteBook(book.getKey());
                    Log.d("BANCO", "Livro deletado");
                }*/
            }
        });
    }
}