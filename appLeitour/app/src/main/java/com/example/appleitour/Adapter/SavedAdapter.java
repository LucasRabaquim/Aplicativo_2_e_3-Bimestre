package com.example.appleitour.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appleitour.Api.NetWorkUtils.AsyncResponse;
import com.example.appleitour.Api.NetWorkUtils.NetworkTask;
import com.example.appleitour.Api.NetWorkUtils.NetworkUtils;
import com.example.appleitour.Controller.BookActivity;
import com.example.appleitour.Controller.MainActivity;
import com.example.appleitour.Model.Annotation;
import com.example.appleitour.Model.Book;
import com.example.appleitour.Model.SharedSettings;
import com.example.appleitour.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.SavedViewHolder> implements AsyncResponse {
    Context context;
    Activity activity;
    ArrayList<Book> books;
    public SavedAdapter(@NonNull Context context, ArrayList<Book> _books) {
        this.context = context;
        this.books = _books;
    }

    @NonNull
    @Override
    public SavedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SavedViewHolder(
                LayoutInflater.from(context).inflate(R.layout.saved_book_item, parent, false)
        );
    }


    @Override
    public void onBindViewHolder(@NonNull SavedViewHolder holder, int position) {
        Book book = books.get(position);
        Picasso.get().load(book.getCover()).into(holder.cover);
        holder.title.setText(book.getName());
        holder.author.setText(book.getAuthor());

        Picasso.get().load(book.getCover()).into(holder.cover);
        holder.mainLayout.setOnClickListener(view -> {
            SharedSettings sharedSettings = new SharedSettings();
            if(context instanceof MainActivity){
                Intent intent = new Intent(context.getApplicationContext(), BookActivity.class);
                intent.putExtra("Book",book);
                context.startActivity(intent);
            }else{
                String token = sharedSettings.GetToken();
                String url = ("SavedBooks/" + book.getSavedId() + "/annotation");
                NetworkTask task = new NetworkTask((Activity) context.getApplicationContext());
                task.execute(NetworkUtils.POST, url, token, null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.books.size();
    }


public class SavedViewHolder extends RecyclerView.ViewHolder {
    ImageView cover;
    TextView title, author;
    LinearLayout mainLayout;

    public SavedViewHolder(@NonNull View view) {
        super(view);
        this.title = view.findViewById(R.id.saved_book_title);
        this.author = view.findViewById(R.id.saved_book_author);
        this.cover = view.findViewById(R.id.saved_book_cover);
        mainLayout = view.findViewById(R.id.saved_book_layout);
    }


}

    @Override
    public void processFinish(String output) {
        Intent intent = new Intent(context, Annotation.class);

        try {
            ArrayList<Annotation> anotacoes = new ArrayList<>();
            JsonArray jsonArray = new JsonParser().parse(output).getAsJsonArray();
            ArrayList<Annotation> apiAnnotation = new ArrayList();
            for (int i = 0, l = jsonArray.size(); i < l; i++) {
                Gson gson = new Gson();
                Annotation anotacao = gson.fromJson(jsonArray.get(i).toString(),  Annotation.class);
                anotacoes.add(anotacao);
            }
            intent.putExtra("ANOTACOES",apiAnnotation);
            context.startActivity(intent);
        }catch(Exception e){}
    }
}