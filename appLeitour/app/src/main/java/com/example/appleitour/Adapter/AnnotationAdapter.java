package com.example.appleitour.Adapter;

import static android.app.PendingIntent.getActivity;
import static android.content.Intent.getIntent;
import static androidx.core.app.ActivityCompat.recreate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appleitour.Controller.AnnotationActivity;
import com.example.appleitour.Controller.BookActivity;
import com.example.appleitour.Database.DatabaseHelper;
import com.example.appleitour.Model.Annotation;
import com.example.appleitour.Model.Book;
import com.example.appleitour.R;

import java.util.ArrayList;

public class AnnotationAdapter extends RecyclerView.Adapter<AnnotationAdapter.ViewHolder> {
    Context context;
    ArrayList<Annotation> annotations;
    Book book;
    public AnnotationAdapter(@NonNull Context context, ArrayList<Annotation> _annotations, Book book) {
        this.context = context;
        this.annotations = _annotations;
        this.book = book;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView annotation, author, book;
        Button btnUpdate,btnDelete;
        public ViewHolder(@NonNull View view){
            super(view);
            this.annotation= view.findViewById(R.id.item_annotation_text);
            this.author= view.findViewById(R.id.item_annotation_author);
            this.book = view.findViewById(R.id.item_annotation_book);
            this.btnUpdate = view.findViewById(R.id.btn_annotation_edit);
            this.btnDelete = view.findViewById(R.id.btn_annotation_discard);
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
        holder.btnUpdate.setOnClickListener(view -> {
            Intent intent = new Intent(context, AnnotationActivity.class);
            intent.putExtra("Book",book);
            intent.putExtra("UserBook",annotation.getUserBookId());
            Log.d("Id da bagaça",String.valueOf(annotation.getUserBookId()));
            intent.putExtra("Annotation", annotation);
            intent.putExtra("AnnotationUpdate", true);
            context.startActivity(intent);
        });
        holder.btnDelete.setOnClickListener(view -> {
            Resources res = context.getResources();
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(res.getString(R.string.dialog_title));
            builder.setMessage(res.getString(R.string.dialog_excluir));
            builder.setPositiveButton(res.getString(R.string.dialog_option_yes), (dialogInterface, i) -> {
                DatabaseHelper db = new DatabaseHelper(view.getContext());
                db.getWritableDatabase();
                db.deleteAnnotation(String.valueOf(annotation.getId()));
                Intent intent = new Intent(context.getApplicationContext(), BookActivity.class);
                intent.putExtra("Book",book);
                context.startActivity(intent);
            });
            builder.setNegativeButton(res.getString(R.string.dialog_option_no), (dialogInterface, i) ->
                    Toast.makeText(context,res.getString(R.string.dialog_result_no),Toast.LENGTH_LONG).show()
            );
            builder.create().show();
        });
    }

    @Override
    public int getItemCount() {
        return this.annotations.size();
    }
}