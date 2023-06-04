package com.leitour.View;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import com.leitour.R;

import java.util.ArrayList;

public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList _id, _nome, _email, _mensagem;

    SavedAdapter(Activity activity, Context context, ArrayList _id, ArrayList _nome, ArrayList _email, ArrayList _mensagem){
        this.activity = activity;
        this.context = context;
        this._id = _id;
        this._nome = _nome;
        this._email = _email;
        this._mensagem = _mensagem;
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
   /*     holder._id.setText(String.valueOf(_id.get(position)));
        holder._nome.setText(String.valueOf(_nome.get(position)));
        holder._email.setText(String.valueOf(_email.get(position)));
        holder._mensagem.setText(String.valueOf(_mensagem.get(position)));

        holder.saved_book_layout.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("book", book);
            activity.startActivityForResult(intent, 1);
        });*/
    }

    @Override
    public int getItemCount() {return _id.size();}
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView cover;
        TextView title, alteration;
        LinearLayout saved_book_layout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.saved_book_cover);
            title = itemView.findViewById(R.id.saved_book_title);
            alteration = itemView.findViewById(R.id.saved_book_last_alteration);
            saved_book_layout = itemView.findViewById(R.id.saved_book_layout);
        }
    }
}