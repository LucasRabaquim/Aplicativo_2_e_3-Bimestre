package com.leitour.View;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.leitour.Model.Book;
import com.leitour.R;

import java.util.ArrayList;

public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList _books;

    SavedAdapter(Activity activity, Context context, ArrayList _books){
        this.activity = activity;
        this.context = context;
        this._books = _books;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.saved_book_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Book book = (Book) _books.get(position);
        holder.cover.setImageResource(book.getCover());
        holder.title.setText(book.getName());
        holder.author.setText(book.getAuthor());

        holder.saved_book_layout.setOnClickListener(view -> {
            Intent intent = new Intent(context, BookActivity.class);
            intent.putExtra("book", book);
            activity.startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //  @Override
    //public int getItemCount() {return _isbn.size();}
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView cover;
        TextView title, author;
        LinearLayout saved_book_layout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.saved_book_cover);
            title = itemView.findViewById(R.id.saved_book_title);
            author = itemView.findViewById(R.id.saved_book_author);
            saved_book_layout = itemView.findViewById(R.id.saved_book_layout);
        }
    }
}