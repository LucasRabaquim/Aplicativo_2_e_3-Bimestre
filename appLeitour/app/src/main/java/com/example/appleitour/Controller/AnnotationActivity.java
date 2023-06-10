package com.example.appleitour.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appleitour.Database.DatabaseHelper;
import com.example.appleitour.Model.Annotation;
import com.example.appleitour.Model.Book;
import com.example.appleitour.Model.UserBook;
import com.example.appleitour.R;


public class AnnotationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        Button btnSaveUpdate, btnCancel;
        Book book = (Book) getIntent().getSerializableExtra("Book");
        Annotation annotation = (Annotation) getIntent().getSerializableExtra("Annotation");
        boolean updateMode = getIntent().getBooleanExtra("AnnotationUpdate",false);
        int userId = getIntent().getIntExtra("userId",0);
        EditText editText = findViewById(R.id.edit_annotation);
        if(annotation != null)
            editText.setText(annotation.getAnnotation());
        btnSaveUpdate = findViewById(R.id.btn_annotation_save);
        btnCancel = findViewById(R.id.btn_annotation_cancel);
        if(annotation != null){
            editText.setText(annotation.getAnnotation());
        }

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        db.getReadableDatabase();
        btnSaveUpdate.setOnClickListener(view -> {
            String text = editText.getText().toString().trim();
            String bookId = book.getKey();
            int userBook = db.selectUserBookId(bookId,userId);
            Annotation userAnnotation = new Annotation(userBook,text,book.getAuthor(),book.getName());
            db.getWritableDatabase();
            Log.d("Atualizar?",String.valueOf(updateMode));
            if(updateMode)
                db.updateAnnotation(userAnnotation);
            else
                db.insertAnnotation(userAnnotation);
            Intent intent = new Intent(getApplicationContext(),BookActivity.class);
            intent.putExtra("Book",book);
            startActivity(intent);
            finish();
        });
        btnCancel.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),BookActivity.class);
            intent.putExtra("Book",book);
            startActivity(intent);
            finish();
        });
    }
}