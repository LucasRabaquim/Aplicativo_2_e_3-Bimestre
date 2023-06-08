package com.leitour.View;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.leitour.Model.Book;
import com.leitour.R;
import java.util.ArrayList;

public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.ViewHolder> {
    Context context;
    ArrayList<Book> books;
    public SavedAdapter(@NonNull Context context, ArrayList<Book> _books) {
        this.context = context;
        this.books = _books;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView cover;
        TextView title, author;
        LinearLayout mainLayout;
        public ViewHolder(@NonNull View view){
            super(view);
            this.title= view.findViewById(R.id.saved_book_title);
            this.author= view.findViewById(R.id.saved_book_author);
            this.cover= view.findViewById(R.id.saved_book_cover);
            mainLayout = view.findViewById(R.id.saved_book_layout);
        }
    }

    @NonNull
    @Override
    public SavedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.saved_book_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SavedAdapter.ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.title.setText(book.getName());
        holder.author.setText(book.getAuthor());
       // Bitmap bitmap = BitmapFactory.decodeByteArray(book.getCover() , 0, book.getCover() .length);
        //holder.cover.setImageBitmap(bitmap);

        Log.d("Titulo", (String) holder.title.getText());
        Log.d("author", (String) holder.author.getText());
        holder.mainLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, BookActivity.class);
            intent.putExtra("Book", book);
            context.startActivity(intent);
        });
    }

        @Override
    public int getItemCount() {
        return this.books.size();
    }
}
