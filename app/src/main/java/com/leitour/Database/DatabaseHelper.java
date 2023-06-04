package com.leitour.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.leitour.Model.*;
import com.leitour.Model.Book;

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

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryTbBook = "CREATE TABLE " + TbBook.TABLE_NAME + "(" +
                "   " + TbBook.COLUMN_ID + " INTEGER PRIMARY KEY," +
                "   " + TbBook.COLUMN_NAME + " TEXT NOT NULL," +
                "   " + TbBook.COLUMN_AUTHOR + " TEXT NOT NULL," +
                "   " + TbBook.COLUMN_PAGES + " INTEGER NOT NULL," +
                "   " + TbBook.COLUMN_EDITION + " INTEGER NOT NULL," +
                "   " + TbBook.COLUMN_COVER + " BLOB," +
                "   " + TbBook.COLUMN_SINOPSE + " TEXT," +
                "   " + TbBook.COLUMN_YEAR + " TEXT NOT NULL," +
                "   " + TbBook.COLUMN_LANGUAGE + " TEXT NOT NULL);" ;

        String queryTbUser = "CREATE TABLE " + TbUser.TABLE_NAME + "(" +
        "   " + TbUser.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "   " + TbUser.COLUMN_NAME + " TEXT, " +
                "   " + TbUser.COLUMN_EMAIL + " TEXT, " +
                "   " + TbUser.COLUMN_PASSWORD + " TEXT, " +
                "   " + TbUser.COLUMN_PHOTO + " BLOB, " +
                "   " + TbUser.COLUMN_COLOR + " int " +
                ");" ;


        String queryTbAnnotation = "CREATE TABLE " + TbAnnotation.TABLE_NAME + "(" +
                "   " + TbAnnotation.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   " + TbAnnotation.COLUMN_USERID + " INTEGER," +
                "   " + TbAnnotation.COLUMN_ANNOTATION + " TEXT," +
                "   " + TbAnnotation.COLUMN_AUTHOR + " TEXT," +
                "   " + TbAnnotation.COLUMN_BOOK + " TEXT" +
                "    FOREIGN KEY("+ TbAnnotation.COLUMN_USERID +") REFERENCES " + TbUser.TABLE_NAME+"("+TbUser.COLUMN_ID+");" ;

        String query = queryTbBook;
        db.execSQL(query);

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
        contentValues.put(TbBook.COLUMN_NAME, book.getName());
        contentValues.put(TbBook.COLUMN_AUTHOR, book.getAuthor());
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

    public Cursor selectBooks(){
        String query = "SELECT * FROM " + TbBook.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null)
            cursor = db.rawQuery(query, null);
        return cursor;
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
/*
    @Nullable
    public User selectUser(<nullabel>int id, String email, String password){
        if(id == 0){

        }
    }
*/







/*
    public Cursor selectReview(){
        String query = "SELECT * FROM " + TABLE_BOOK_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null)
            cursor = db.rawQuery(query, null);
        return cursor;
    }

    public void updateReview(String _id, String _name, String _author, String _pages){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BOOK_COLUMN_NAME, _name);
        cv.put(BOOK_COLUMN_EMAIL, _author);
        cv.put(BOOK_COLUMN_MENSAGEM, _pages);
        long result = db.update(TABLE_BOOK_NAME, cv, "_id=?", new String[]{_id});
        String alerta = (result == -1) ? "Falha na alteração," : "Alteração realizada com sucesso.";
        Toast.makeText(context, alerta, Toast.LENGTH_SHORT).show();
    }

    public void deleteOneRow(String _id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_BOOK_NAME, "_id=?", new String[]{_id});
        String alerta = (result == -1) ? "Falha na exclusão da review," : "Review excluida com sucesso.";
        Toast.makeText(context, alerta, Toast.LENGTH_SHORT).show();
    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_BOOK_NAME);
    }
    public ArrayList<reviewClass> selectListaReview() {
        ArrayList<reviewClass> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_BOOK_NAME, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            reviewClass reviewClass = new reviewClass();
            int i = res.getColumnIndex(BOOK_COLUMN_NAME);
            reviewClass.set_nome(res.getString(i));
            i = res.getColumnIndex(BOOK_COLUMN_ISBN);
            reviewClass.set_id(Integer.parseInt(res.getString(i)));
            i = res.getColumnIndex(BOOK_COLUMN_EMAIL);
            reviewClass.set_email(res.getString(i));
            i = res.getColumnIndex(BOOK_COLUMN_MENSAGEM);
            reviewClass.set_mensagem(res.getString(i));
            lista.add(reviewClass);
            res.moveToNext();
        }
        res.close();
        return lista;
    }*/
}
