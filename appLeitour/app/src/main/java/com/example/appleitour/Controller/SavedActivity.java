package com.example.appleitour.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.appleitour.Adapter.SavedAdapter;
import com.example.appleitour.Database.DatabaseHelper;
import com.example.appleitour.Model.Book;
import com.example.appleitour.R;
import com.example.appleitour.SimpleAppWidget;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SavedActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

        // Obtém os IDs dos widgets do seu app
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, SimpleAppWidget.class));

        // Atualiza os widgets
        if (appWidgetIds != null && appWidgetIds.length > 0) {
            SimpleAppWidget simpleAppWidget = new SimpleAppWidget();
            simpleAppWidget.onUpdate(this, appWidgetManager, appWidgetIds);
        }

        recyclerView = findViewById(R.id.recycler_saved_book);
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        SharedPreferences settings = getSharedPreferences("com.example.appleitour", 0);
        int userId = settings.getInt("UserId", 0);

        ArrayList<Book> books = databaseHelper.selectBooks(userId);
        SavedAdapter savedAdapter = new SavedAdapter(this,books);
        recyclerView.setAdapter(savedAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavBar);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent intent;
            Class classe;
            int itemId = item.getItemId();
            if (itemId == R.id.nav_saved)
                classe = SavedActivity.class;
            else if (itemId == R.id.nav_search)
                classe = MainActivity.class;
            else
                classe = CadastrarActivity.class;
            intent = new Intent(getApplicationContext(), classe);
            startActivity(intent);
            finish();
            return false;
        });
    }
}