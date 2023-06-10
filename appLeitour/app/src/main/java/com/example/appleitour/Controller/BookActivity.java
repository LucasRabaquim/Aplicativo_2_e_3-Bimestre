package com.example.appleitour.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.appleitour.Database.DatabaseHelper;
import com.example.appleitour.Model.Book;
import com.example.appleitour.Model.User;
import com.example.appleitour.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BookActivity extends AppCompatActivity {

  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        FloatingActionButton btnSave = findViewById(R.id.btn_save_book);
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        db.getReadableDatabase();
        Book book = (Book) getIntent().getSerializableExtra("Book");
        TextView title = findViewById(R.id.book_title);
        TextView author = findViewById(R.id.book_author);
        TextView publisher = findViewById(R.id.book_publisher);
        TextView year = findViewById(R.id.book_year);

        title.setText(book.getName());
        author.setText(book.getAuthor());
        publisher.setText(book.getPublisher());
        year.setText(book.getYear());

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        User user = new User("Lucas","123","email",(byte)123,123);
        dbHelper.insertUser(user);

        btnSave.setOnClickListener(view -> {
            SharedPreferences settings = getSharedPreferences("com.example.appleitour", 0);
            int userId = 1;//settings.getInt("userId", 1);
            Log.d("Livros",String.valueOf(db.selectBooks()));
            Log.d("Livros Stored",String.valueOf(db.checkBookIsStored(book.getIsbn())));
            Log.d("Livros Salvo",String.valueOf(db.checkBookIsSaved(book.getIsbn())));
            Log.d("Livros USERBOOK",String.valueOf(db.checkUserBook(userId,book.getIsbn())));



            db.insertBook(book);
            db.insertUserBook(userId, book.getIsbn());


            if(db.checkBookIsStored(book.getIsbn())) {

                Log.d("BANCO","Livro inserido");
            }
            if(db.checkUserBook(userId,book.getIsbn())) {

                Log.d("BANCO","Relação criada");
            }
      /*      else {
                db.deleteUserBook(userId, book.getIsbn());
                Log.d("BANCO","Relação deletada");
                if(!db.checkBookIsSaved(book.getIsbn())) {
                    db.deleteBook(book.getIsbn());
                    Log.d("BANCO", "Livro deletado");
                }
            }*/
        });
    }
}