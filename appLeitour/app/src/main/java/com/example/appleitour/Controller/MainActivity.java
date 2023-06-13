package com.example.appleitour.Controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import com.example.appleitour.Adapter.BookAdapter;
import com.example.appleitour.Api.NetworkUtils;
import com.example.appleitour.Model.Book;
import com.example.appleitour.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>>{

    private ListView dataListView;
    private EditText requestTag;
    private TextView errorMessage;
    private ProgressBar loadingBar;
    private BookAdapter adapter;
    private static final int BOOK_SEARCH_LOADER = 1;
    private static final String BOOK_QUERY_TAG = "query";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingBar = findViewById(R.id.loadingBar);
        errorMessage = findViewById(R.id.errorMessage);
        requestTag = findViewById(R.id.requestTag);

        dataListView = findViewById(R.id.dataListView);
        dataListView.setEmptyView(errorMessage);

        adapter = new BookAdapter(getApplicationContext());
        dataListView.setAdapter((adapter));

        dataListView.setOnItemClickListener((adapterView, view, i, l) -> {
            Book book = (Book) adapterView.getItemAtPosition(i);
            Intent intent = new Intent(getApplicationContext(), BookActivity.class);
            intent.putExtra("Book",book);
            startActivity(intent);
        });


        if(savedInstanceState != null) {
            String queryUrl = savedInstanceState.getString((BOOK_QUERY_TAG));
            requestTag.setText((queryUrl));
        }

        LoaderManager.getInstance(this).initLoader(BOOK_SEARCH_LOADER, null, this);

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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString(BOOK_QUERY_TAG, requestTag.getText().toString().trim());
    }

    @NonNull
    @SuppressLint("StaticFieldLeak")
    public Loader<List<Book>> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<List<Book>>(this) {
            List<Book> mBookList;
            @Override
            protected void onStartLoading() {
                if (args == null) {
                    return;
                }

                loadingBar.setVisibility(View.VISIBLE);

                if (mBookList != null) {
                    deliverResult(mBookList);
                } else {
                    forceLoad();
                }
            }

            @Nullable
            @Override
            public List<Book> loadInBackground() {
                String searchQueryUrlString = args.getString(BOOK_QUERY_TAG);

                try {
                    return NetworkUtils.getDataFromApi(searchQueryUrlString);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public void deliverResult(@Nullable List<Book> data) {
                mBookList = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Book>> loader, List<Book> data) {
        loadingBar.setVisibility(View.INVISIBLE);

        if(null == data)
            showErrorMessage();
        else {
            adapter.clear();
            adapter.addAll(data);
            showJsonDataView();
        }
    }

    public void onLoaderReset(@NonNull Loader<List<Book>> loader) {}

    private void showJsonDataView() {
        errorMessage.setVisibility(View.INVISIBLE);
        dataListView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        dataListView.setVisibility(View.INVISIBLE);
        errorMessage.setVisibility(View.VISIBLE);
    }
    public void searchBook(View view) {
        makeBookSearchQuery();
    }
    private void makeBookSearchQuery() {
        String bookQuery = requestTag.getText().toString();
        Bundle queryBundle = new Bundle();
        queryBundle.putString(BOOK_QUERY_TAG, bookQuery);
        LoaderManager loaderManager = LoaderManager.getInstance(this);
        Loader<String> bookSearchLoader = loaderManager.getLoader(BOOK_SEARCH_LOADER);
        if (bookSearchLoader == null) {
            loaderManager.initLoader(BOOK_SEARCH_LOADER, queryBundle, this);
        } else {
            loaderManager.restartLoader(BOOK_SEARCH_LOADER, queryBundle, this);
        }
    }

}
