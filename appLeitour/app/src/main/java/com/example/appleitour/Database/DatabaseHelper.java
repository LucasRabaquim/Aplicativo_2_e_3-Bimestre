package com.example.appleitour.Database;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.example.appleitour.Model.*;
import com.example.appleitour.Model.Book;
import com.example.appleitour.Database.TbBook;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Leitour.db";
    static TbBook TbBook = new TbBook();
    static TbUser TbUser = new TbUser();
    static TbAnnotation TbAnnotation = new TbAnnotation();

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @SuppressLint("Recycle")
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TbBook.QUERY);
        db.execSQL(TbUser.QUERY);
        db.execSQL(TbAnnotation.QUERY);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        final String[] DB_TABLES = {TbBook.TABLE_NAME,TbUser.TABLE_NAME,TbAnnotation.TABLE_NAME};
        for (String table: DB_TABLES)
            db.execSQL("DROP TABLE IF EXISTS " + table);
        onCreate(db);
    }

    public void insertBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbBook.COLUMN_ID, book.getIsbn());
        contentValues.put(TbBook.COLUMN_NAME, book.getName());
        contentValues.put(TbBook.COLUMN_AUTHOR, book.getAuthor());
        contentValues.put(TbBook.COLUMN_PUBLISHER, book.getPublisher());
        contentValues.put(TbBook.COLUMN_PAGES, book.getPages());
        contentValues.put(TbBook.COLUMN_EDITION, book.getEdition());
        contentValues.put(TbBook.COLUMN_COVER, book.getCover());
        contentValues.put(TbBook.COLUMN_SINOPSE, book.getSinopse());
        contentValues.put(TbBook.COLUMN_YEAR, book.getYear());
        contentValues.put(TbBook.COLUMN_LANGUAGE, book.getLanguage());
        long result = db.insert(TbBook.TABLE_NAME,null, contentValues);
        String alerta = (result == -1) ? "Erro na criação da review" : "Review criada com sucesso.";
        Toast.makeText(context, alerta, Toast.LENGTH_SHORT).show();
    }

    public ArrayList<Book> selectBooks(){
        String query = "SELECT * FROM " + TbBook.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null)
            cursor = db.rawQuery(query, null);
        ArrayList<Book> books = new ArrayList<>();
        if(cursor != null)
           while (cursor.moveToNext()) {
                Book book = new Book();
                book.setIsbn(cursor.getInt(0));
                book.setName(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                book.setPublisher(cursor.getString(3));
                book.setPages(cursor.getInt(4));
                book.setEdition(cursor.getInt(5));
                book.setCover((byte) cursor.getInt(6));
                book.setSinopse(cursor.getString(7));
                book.setYear(cursor.getString(8));
                book.setLanguage(cursor.getString(9));
                books.add(book);
            }
        return books;
    }
    public void insertUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbUser.COLUMN_ID,user.getId());
        contentValues.put(TbUser.COLUMN_NAME, user.getName());
        contentValues.put(TbUser.COLUMN_EMAIL, user.getEmail());
        contentValues.put(TbUser.COLUMN_PASSWORD, user.getPassword());
        contentValues.put(TbUser.COLUMN_PHOTO, user.getPhoto());
        contentValues.put(TbUser.COLUMN_COLOR, user.getColor());
        db.insert(TbUser.TABLE_NAME,null, contentValues);
    }
    public int selectUserId(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + TbUser.COLUMN_ID + " from " + TbUser.TABLE_NAME + " order by " +
                TbUser.COLUMN_ID + " desc limit 1";
        Cursor cursor = db.rawQuery(query, null);
        int id = 0;
            while (cursor.moveToNext())
                id = cursor.getInt(0);
        return id;
    }

    public void insertAnnotation(Annotation annotation){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbAnnotation.COLUMN_ID,annotation.getId());
        contentValues.put(TbAnnotation.COLUMN_BOOKID, annotation.getBookId());
        contentValues.put(TbAnnotation.COLUMN_USERID, annotation.getUserId());
        contentValues.put(TbAnnotation.COLUMN_ANNOTATION, annotation.getAnnotation());
        contentValues.put(TbAnnotation.COLUMN_AUTHOR, annotation.getAuthor());
        contentValues.put(TbAnnotation.COLUMN_BOOK, annotation.getBook());
        db.insert(TbAnnotation.TABLE_NAME,null, contentValues);
    }
    public ArrayList<Annotation> selectAnnotations(int userId, int bookId){
     /*   String query = "SELECT * FROM " + TbAnnotation.TABLE_NAME + " where " +
                TbAnnotation.COLUMN_USERID + " = " + userId +
                " and " + TbAnnotation.COLUMN_BOOKID + " = " + bookId;*/
        String query = "Select * from " + TbAnnotation.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null)
            cursor = db.rawQuery(query, null);
        ArrayList<Annotation> annotations = new ArrayList<>();
        if(cursor != null)
            while (cursor.moveToNext()) {
                Annotation annotation = new Annotation();
                annotation.setBookId(cursor.getInt(0));
                Log.d("BookId:",String.valueOf(cursor.getInt(0)));
                annotation.setUserId(cursor.getInt(1));
                Log.d("UserId:",String.valueOf(cursor.getInt(1)));
                annotation.setAnnotation(cursor.getString(2));
                Log.d("Annotation:",cursor.getString(3));
                annotation.setAuthor(cursor.getString(3));
                Log.d("Break:","=======================");
                annotation.setBook(cursor.getString(4));
                annotations.add(annotation);
            }

        return annotations;
    }
}
