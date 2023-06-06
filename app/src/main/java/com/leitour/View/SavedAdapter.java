package com.leitour.View;
import static androidx.core.content.ContextCompat.startActivity;
import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.leitour.Model.Book;
import com.leitour.R;

import java.util.ArrayList;

public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.SavedHolder> {


    private final ArrayList<Book> _books;

    private Context context;
    public SavedAdapter(Context context, ArrayList<Book> _books){
        this._books = _books;
    }

    @NonNull
    @Override
    public SavedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.saved_book_item, parent, false);
        return new SavedHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SavedHolder holder, @SuppressLint("RecyclerView") final int position) {
        Book book = _books.get(position);
        holder.cover.setImageResource(book.getCover());
        holder.title.setText(book.getName());
        holder.author.setText(book.getAuthor());

        Log.d("Autor", (String) holder.author.getText());
        Log.d("Titulo", (String) holder.title.getText());


        holder.saved_book_layout.setOnClickListener(view -> {
            context.startActivity(new Intent(context, BookActivity.class)
                    .putExtra("book", book));
        });
    }

    @Override
    public int getItemCount() {return 0;}
    class SavedHolder extends RecyclerView.ViewHolder {
        ImageView cover;
        TextView title, author;
        LinearLayout saved_book_layout;

        public SavedHolder(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.saved_book_cover);
            title = itemView.findViewById(R.id.saved_book_title);
            author = itemView.findViewById(R.id.saved_book_author);
            saved_book_layout = itemView.findViewById(R.id.saved_book_layout);
        }
    }
}