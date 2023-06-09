package com.example.appleitour.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appleitour.Database.DatabaseHelper;
import com.example.appleitour.Model.Annotation;
import com.example.appleitour.Model.Book;
import com.example.appleitour.R;


public class AnnotationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        EditText editText;
        Button btnSaveUpdate, btnDiscard;
        Book book = (Book) getIntent().getSerializableExtra("Book");
        int userId = getIntent().getIntExtra("userId",0);
        editText = findViewById(R.id.edit_annotation);
        btnSaveUpdate = findViewById(R.id.btn_annotation_save);
        btnDiscard = findViewById(R.id.btn_annotation_cancel);

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        btnSaveUpdate.setOnClickListener(view -> {
            String text = String.valueOf(editText.getText());
            int bookId = book.getIsbn();
            Annotation annotation = new Annotation(bookId,text,"Espada de assis","Bom casmurro");
            db.insertAnnotation(annotation);
            Intent intent = new Intent(getApplicationContext(),BookActivity.class);
            intent.putExtra("Book",book);
            startActivity(intent);
        });
    }
}