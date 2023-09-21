package com.example.appleitour.Controller;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appleitour.Adapter.SavedAdapter;
import com.example.appleitour.Api.NetWorkUtils.AsyncResponse;
import com.example.appleitour.Api.NetWorkUtils.NetworkTask;
import com.example.appleitour.Api.NetWorkUtils.NetworkUtils;
import com.example.appleitour.Model.Book;
import com.example.appleitour.Model.SharedSettings;
import com.example.appleitour.R;
import com.example.appleitour.SimpleAppWidget;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.ArrayList;

public class SavedActivity extends AppCompatActivity implements AsyncResponse {

    RecyclerView recyclerView;
    private String API_RESPONSE = "Api_Response";

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
        SharedSettings sharedSettings = new SharedSettings();
        String token = sharedSettings.GetToken();

        Toast.makeText(this, "Mostrando livros salvos", Toast.LENGTH_SHORT).show();
        NetworkTask task = new NetworkTask(SavedActivity.this);
        task.execute(NetworkUtils.GET,"SavedBooks",token,null);



        /*
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        SharedPreferences settings = getSharedPreferences("com.example.appleitour", 0);
        int userId = settings.getInt("UserId", 0);

        ArrayList<Book> books = databaseHelper.selectBooks(userId);
        SavedAdapter savedAdapter = new SavedAdapter(this,books);
        recyclerView.setAdapter(savedAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/


        SharedSettings sharedSettings = new SharedSettings(SavedActivity.this);
        String token = sharedSettings.GetToken();
        String url =("books/SavedBooks");
        NetworkTask task = new NetworkTask(SavedActivity.this);
        task.execute(NetworkUtils.POST,url,token,null);

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

    public void processFinish(String out) {
        ArrayList<Book> book = updateRecycleView(out);
        SavedAdapter savedAdapter = new SavedAdapter(this,book);
        recyclerView.setAdapter(savedAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public ArrayList<Book> updateRecycleView(String out){
        try {
            JsonArray jsonArray = new JsonParser().parse(out).getAsJsonArray();
            ArrayList<Book> apiBooks = new ArrayList();
            for (int i = 0, l = jsonArray.size(); i < l; i++) {
                Gson gson = new Gson();
                Book book = gson.fromJson(jsonArray.get(i).toString(),  Book.class);
                apiBooks.add(book);
            }
            return apiBooks;
        }catch(Exception e){}
        return new ArrayList<>();
    }
}