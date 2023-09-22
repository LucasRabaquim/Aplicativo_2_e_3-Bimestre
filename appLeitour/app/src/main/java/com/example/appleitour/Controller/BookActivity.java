package com.example.appleitour.Controller;

import static android.view.View.GONE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appleitour.Adapter.AnnotationAdapter;
import com.example.appleitour.Database.DatabaseHelper;
import com.example.appleitour.Model.Annotation;
import com.example.appleitour.Model.Book;
import com.example.appleitour.R;
import com.example.appleitour.SimpleAppWidget;
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

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

        // Obtém os IDs dos widgets do seu app
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, SimpleAppWidget.class));

        // Atualiza os widgets
        if (appWidgetIds != null && appWidgetIds.length > 0) {
            SimpleAppWidget simpleAppWidget = new SimpleAppWidget();
            simpleAppWidget.onUpdate(this, appWidgetManager, appWidgetIds);
        }

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
        TextView isbn = findViewById(R.id.book_isbn);
        TextView edition = findViewById(R.id.book_edition);
        TextView language = findViewById(R.id.book_language);
        TextView pages = findViewById(R.id.book_pages);

        Resources res = getResources();
        title.setText(book.getName());
        author.setText(res.getString(R.string.author,book.getAuthor()));
        publisher.setText(res.getString(R.string.publisher,book.getPublisher()));
        year.setText(res.getString(R.string.year,String.valueOf(book.getYear())));
        isbn.setText(res.getString(R.string.isbn,book.getIsbn()));
        edition.setText(res.getString(R.string.edition,book.getEdition()));
        language.setText(res.getString(R.string.language,book.getLanguage()));
        pages.setText(res.getString(R.string.pages,book.getPages()));

       /* if(!Objects.equals(book.getSinopse(), "-"))
            sinopse.setText(getResources().getString(R.string.sinopse,book.getSinopse()));*/
        if(Objects.equals(book.getIsbn(), ""))
            isbn.setVisibility(GONE);
        if(book.getEdition() == 0)
            edition.setVisibility(GONE);
        if(Objects.equals(book.getLanguage(), "-"))
            language.setVisibility(GONE);
        if(book.getPages() == 0)
            pages.setVisibility(GONE);

        Picasso.get().load(book.getCover()).into(backgroundCover);
        Picasso.get().load(book.getCover()).into(bookCover);

        RecyclerView recyclerView = findViewById(R.id.recycler_annotation);
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        SharedPreferences settings = getSharedPreferences("com.example.appleitour", 0);
        int userId = settings.getInt("UserId", 0);
        if(!db.checkUserBook(book.getKey(), userId)) {
            btnCreate.setVisibility(View.VISIBLE);
            int userBookId = db.selectBookId(book.getKey(), userId);
            changeIcon(R.color.yellow);
            ArrayList<Annotation> annotation = databaseHelper.selectAnnotations(userBookId);
            AnnotationAdapter annotationAdapter = new AnnotationAdapter(this,annotation,book);
            recyclerView.setAdapter(annotationAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        btnCreate.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AnnotationActivity.class);
            intent.putExtra("Book",book);
            int userBookId = db.selectBookId(book.getKey(), userId);
            intent.putExtra("UserBook",userBookId);
            startActivity(intent);
            finish();
        });

        btnSave.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), BookActivity.class);
            intent.putExtra("Book",book);
            int userBookId = db.selectBookId(book.getKey(), userId);
            intent.putExtra("UserBook",userBookId);
            if(db.checkBookIsStored(book.getKey())) {// Se o livro nao estiver no banco, salva
                db.insertBook(book);
                db.insertUserBook(book.getKey(),userId);
                changeIcon(R.color.yellow);
                startActivity(intent);
                finish();
            }
            else if(db.checkUserBook(book.getKey(), userId)){// Se ele ta no banco, mas não salvei ele, salva pra mim
                db.insertUserBook(book.getKey(),userId);
                changeIcon(R.color.yellow);
                startActivity(intent);
                finish();
            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(res.getString(R.string.dialog_title));
                builder.setMessage(res.getString(R.string.dialog_unsave));
                builder.setPositiveButton(res.getString(R.string.dialog_option_yes), (dialogInterface, i) -> {
                    db.deleteUserBook(book.getKey(),userId);
                    startActivity(new Intent(getApplicationContext(), SavedActivity.class));
                    finish();
                });
                builder.setNegativeButton(res.getString(R.string.dialog_option_no), (dialogInterface, i) ->
                    Toast.makeText(this,res.getString(R.string.dialog_result_no),Toast.LENGTH_LONG).show()
                );
                builder.create().show();
            }
            if(db.checkBookIsSaved(book.getKey())){
                db.deleteBook(book.getKey());
            }
        });
    }
    void changeIcon(int color){
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(getApplicationContext(), R.drawable.save);
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, getResources().getColor(color,getTheme()));
    }
}