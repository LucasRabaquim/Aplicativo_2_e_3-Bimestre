package com.example.appleitour.Controller;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appleitour.Adapter.SavedAdapter;
import com.example.appleitour.Api.NetWorkUtils.AsyncResponse;
import com.example.appleitour.Api.NetWorkUtils.NetworkTask;
import com.example.appleitour.Api.NetWorkUtils.NetworkUtils;
import com.example.appleitour.Model.Book;
import com.example.appleitour.R;
import com.example.appleitour.SimpleAppWidget;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AsyncResponse {
    private EditText searchBar;
    private RecyclerView recyclerView;
    private SavedAdapter savedAdapter;
    private TextView errorMessage;
    private ProgressBar loadingBar;
    private ArrayList<Book> books;
    private Button btnSearchBook;

    private String API_RESPONSE = "Api_Response";
    private static final int BOOK_SEARCH_LOADER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String stringBooks = intent.getStringExtra(API_RESPONSE);
        ArrayList<Book> apiBooks = NetworkUtils.jsonToBookList(stringBooks);

        Toast.makeText(this,"Ent: "+stringBooks,Toast.LENGTH_SHORT).show();
        books.addAll(apiBooks);
        recyclerView = findViewById(R.id.recycler_returned_books);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        savedAdapter = new SavedAdapter(MainActivity.this,apiBooks);
        recyclerView.setAdapter(savedAdapter);

        loadingBar = findViewById(R.id.loadingBar);
        errorMessage = findViewById(R.id.errorMessage);
        searchBar = findViewById(R.id.searchBookBar);
        btnSearchBook = findViewById(R.id.btn_SearchBook);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

        // Obtém os IDs dos widgets do seu app
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, SimpleAppWidget.class));

        // Atualiza os widgets
        if (appWidgetIds != null && appWidgetIds.length > 0) {
            SimpleAppWidget simpleAppWidget = new SimpleAppWidget();
            simpleAppWidget.onUpdate(this, appWidgetManager, appWidgetIds);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavBar);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent switchIntent;
            Class classe;
            int itemId = item.getItemId();
            if (itemId == R.id.nav_saved)
                classe = SavedActivity.class;
            else if (itemId == R.id.nav_search)
                classe = MainActivity.class;
            else
                classe = CadastrarActivity.class;
            switchIntent = new Intent(getApplicationContext(), classe);
            startActivity(switchIntent);
            finish();
            return false;
        });

        btnSearchBook.setOnClickListener(view ->{
            String bookQuery = searchBar.getText().toString();
            String url =("SearchBy/Title/"+bookQuery).replace(" ","+");
            Toast.makeText(this, "Pesquisando por: "+bookQuery, Toast.LENGTH_SHORT).show();
            NetworkTask task = new NetworkTask(MainActivity.this);
            task.execute(NetworkUtils.GET,url,null,null);
        });
    }

    private void showJsonDataView() {
        errorMessage.setVisibility(View.INVISIBLE);
    }
    private void showErrorMessage() {
        errorMessage.setVisibility(View.VISIBLE);
    }

    public void processFinish(String out) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtra(API_RESPONSE,out);
        startActivity(intent);
        //finish();
    }
}
