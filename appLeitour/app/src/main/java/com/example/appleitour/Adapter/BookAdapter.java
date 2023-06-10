package com.example.appleitour.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.appleitour.Model.Book;
import com.example.appleitour.R;

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(@NonNull Context context) {
        super(context, 0);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.respository_item, parent, false);


        TextView bookName = view.findViewById(R.id.bookName);
        TextView bookAuthor = view.findViewById(R.id.bookAuthor);
        TextView bookEditora = view.findViewById(R.id.bookEditora);
        TextView bookLang = view.findViewById(R.id.bookLang);
        TextView bookDate = view.findViewById(R.id.bookDate);

        Book currentBook = getItem(position);
        bookName.setText(String.valueOf(currentBook.getName()));
        bookAuthor.setText(currentBook.getAuthor());
        bookEditora.setText(currentBook.getPublisher());
        bookLang.setText(currentBook.getLanguage());
        bookDate.setText(currentBook.getYear());

        return view;
    }
}
