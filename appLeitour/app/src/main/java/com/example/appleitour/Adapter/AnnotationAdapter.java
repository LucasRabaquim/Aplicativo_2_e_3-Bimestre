package com.example.appleitour.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appleitour.Controller.AnnotationActivity;
import com.example.appleitour.Model.Annotation;
import com.example.appleitour.Model.Book;
import com.example.appleitour.R;

import java.util.ArrayList;

public class AnnotationAdapter extends RecyclerView.Adapter<AnnotationAdapter.ViewHolder> {
    Context context;
    ArrayList<Annotation> annotations;
    public AnnotationAdapter(@NonNull Context context, ArrayList<Annotation> _annotations) {
        this.context = context;
        this.annotations = _annotations;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView annotation, author, book;
        LinearLayout mainLayout;
        public ViewHolder(@NonNull View view){
            super(view);
            this.annotation= view.findViewById(R.id.item_annotation_text);
            this.author= view.findViewById(R.id.item_annotation_author);
            this.book = view.findViewById(R.id.item_annotation_book);
            mainLayout = view.findViewById(R.id.item_annotation_layout);
        }
    }

    @NonNull
    @Override
    public AnnotationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AnnotationAdapter.ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.annotation_book_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AnnotationAdapter.ViewHolder holder, int position) {
        Annotation annotation = annotations.get(position);
        holder.annotation.setText(annotation.getAnnotation());
        holder.author.setText(annotation.getAuthor());
        holder.book.setText(annotation.getBook());
        holder.mainLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, AnnotationActivity.class);
            intent.putExtra("Annotation", annotation);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return this.annotations.size();
    }
}
