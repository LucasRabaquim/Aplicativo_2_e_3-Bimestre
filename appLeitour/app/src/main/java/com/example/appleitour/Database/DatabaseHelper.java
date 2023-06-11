package com.example.appleitour.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.appleitour.Model.*;
import com.example.appleitour.Model.Book;

import java.io.File;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    Context context;
    private static final String DATABASE_NAME = "Leitour.db";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String[] DB_QUERY = {TbBook.QUERY,TbUser.QUERY,TbAnnotation.QUERY,TbUserBook.QUERY};
        for (String QUERY: DB_QUERY)
            db.execSQL(QUERY);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        final String[] DB_TABLES = {TbBook.TABLE_NAME,TbUser.TABLE_NAME,TbAnnotation.TABLE_NAME,TbUserBook.TABLE_NAME};
        for (String table : DB_TABLES)
            db.execSQL("DROP TABLE IF EXISTS " + table);
        onCreate(db);
    }
    public void insertBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbBook.COLUMN_ID, book.getKey());
        contentValues.put(TbBook.COLUMN_ISBN, book.getIsbn());
        contentValues.put(TbBook.COLUMN_NAME, book.getName());
        contentValues.put(TbBook.COLUMN_AUTHOR, book.getAuthor());
        contentValues.put(TbBook.COLUMN_PUBLISHER, book.getPublisher());
        contentValues.put(TbBook.COLUMN_PAGES, book.getPages());
        contentValues.put(TbBook.COLUMN_EDITION, book.getEdition());
        contentValues.put(TbBook.COLUMN_COVER, book.getCover());
        contentValues.put(TbBook.COLUMN_SINOPSE, book.getSinopse());
        contentValues.put(TbBook.COLUMN_YEAR, book.getYear());
        contentValues.put(TbBook.COLUMN_LANGUAGE, book.getLanguage());
        db.insert(TbBook.TABLE_NAME,null, contentValues);
    }
    public ArrayList<Book> selectBooks(int userId){
        String query =" SELECT * FROM " + TbBook.TABLE_NAME ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null)
            cursor = db.rawQuery(query, null);
        ArrayList<Book> books = new ArrayList<>();
        if(cursor != null) {
            while (cursor.moveToNext()) {
                Book book = new Book();
                book.setKey(cursor.getString(0));
                if(this.selectLastInsertBookId(book.getKey(), userId) == 0)
                   continue;
                book.setIsbn(cursor.getInt(1));
                book.setName(cursor.getString(2));
                book.setAuthor(cursor.getString(3));
                book.setPublisher(cursor.getString(4));
                book.setPages(cursor.getInt(5));
                book.setEdition(cursor.getInt(6));
                book.setCover(cursor.getString(7));
                book.setSinopse(cursor.getString(8));
                book.setYear(cursor.getString(9));
                book.setLanguage(cursor.getString(10));
                books.add(book);
                book.debug(this.selectLastInsertBookId(book.getKey(), userId));
            }
            cursor.close();
        }
        return books;
    }
    public boolean checkBookIsStored(String bookId){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + TbBook.TABLE_NAME + " where " +
                TbBook.COLUMN_ID + " =?";
        Cursor cursor = null;
        if(db != null)
            cursor = db.rawQuery(query, new String[]{bookId});
        return cursor.getCount() == 0;
    }
    public boolean checkBookIsSaved(String bookId){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + TbUserBook.TABLE_NAME + " where " +
                TbUserBook.COLUMN_BOOKID + " =?";
        Cursor cursor = null;
        if(db != null)
            cursor = db.rawQuery(query, new String[]{bookId});
        return cursor.getCount() == 0;
    }

    public void deleteBook(String bookId){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TbBook.TABLE_NAME, TbBook.COLUMN_ID+"=?", new String[]{bookId});
    }

    public void insertUser(@NonNull User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbUser.COLUMN_NAME, user.getName());
        contentValues.put(TbUser.COLUMN_EMAIL, user.getEmail());
        contentValues.put(TbUser.COLUMN_PASSWORD, user.getPassword());
        contentValues.put(TbUser.COLUMN_PHOTO, user.getPhoto());
        contentValues.put(TbUser.COLUMN_COLOR, user.getColor());
        db.insert(TbUser.TABLE_NAME,null, contentValues);
    }

    public int selectLastInsert(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select last_insert_rowid();";
        Cursor cursor = null;
        if(db != null)
            cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }
    public boolean verificarUsuarioCadastrado(String email, String senha){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + TbUser.TABLE_NAME + " where " +
                TbUser.COLUMN_EMAIL + " =? " + " AND " + TbUser.COLUMN_PASSWORD + " =? ";
        Cursor cursor = null;
        if(db != null)
            cursor = db.rawQuery(query, new String[]{email,senha});
        return cursor.getCount() > 0;
    }

    public void insertUserBook(String bookId, int userId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbUserBook.COLUMN_BOOKID, bookId);
        contentValues.put(TbUserBook.COLUMN_USERID, userId);
        db.insert(TbUserBook.TABLE_NAME,null, contentValues);
    }
    @SuppressLint("Recycle")
    public boolean checkUserBook(String bookId, int userId){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + TbUserBook.TABLE_NAME + " where " +
                TbUserBook.COLUMN_BOOKID + " =? " + " AND " + TbUserBook.COLUMN_USERID + " =? ";
        Cursor cursor = null;
        if(db != null)
            cursor = db.rawQuery(query, new String[]{bookId,String.valueOf(userId)});
        return cursor.getCount() == 0;
    }

    @SuppressLint("Range")
    public int selectLastInsertBookId(String bookId, int userId){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select " + TbUserBook.COLUMN_ID + " from " + TbUserBook.TABLE_NAME + " where " +
                TbUserBook.COLUMN_BOOKID + " =?" + " AND " + TbUserBook.COLUMN_USERID + " =?";
        Cursor cursor = null;
        if(db != null)
            cursor = db.rawQuery(query, new String[]{bookId,String.valueOf(userId)});
        if(cursor != null){
            cursor.moveToFirst();
            return (cursor.getCount() > 0) ? cursor.getInt(0) : 0;
        }
        return 0;
    }

    public void deleteUserBook(String bookId, int userId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TbUserBook.TABLE_NAME, "_BookId=? AND _UserId=?", new String[]{bookId, String.valueOf(userId)});
    }

    public void insertAnnotation(Annotation annotation){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbAnnotation.COLUMN_USERBOOKID, annotation.getUserBookId());
        contentValues.put(TbAnnotation.COLUMN_ANNOTATION, annotation.getAnnotation());
        contentValues.put(TbAnnotation.COLUMN_AUTHOR, annotation.getAuthor());
        contentValues.put(TbAnnotation.COLUMN_BOOK, annotation.getBook());
        long test = db.insert(TbAnnotation.TABLE_NAME,null, contentValues);

    }

    public ArrayList<Annotation> selectAnnotations(int userBook){
        String query = "Select * from " + TbAnnotation.TABLE_NAME + " where " + TbAnnotation.COLUMN_USERBOOKID + "=" + userBook;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null)
            cursor = db.rawQuery(query, null);
        ArrayList<Annotation> annotations = new ArrayList<>();
        if(cursor != null) {
            while (cursor.moveToNext()) {
                Annotation annotation = new Annotation();
                annotation.setId(cursor.getInt(0));
                annotation.setUserBookId(cursor.getInt(1));
                annotation.setAnnotation(cursor.getString(2));
                annotation.setAuthor(cursor.getString(3));
                annotation.setBook(cursor.getString(4));
                annotations.add(annotation);
                Log.d( "selectAnnotations nome: ",annotation.getAnnotation());
            }
        cursor.close();
        }
        return annotations;
    }

    public void updateAnnotation(Annotation annotation){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TbAnnotation.COLUMN_ID, annotation.getId());
        cv.put(TbAnnotation.COLUMN_ANNOTATION, annotation.getAnnotation());
        cv.put(TbAnnotation.COLUMN_BOOK, annotation.getBook());
        cv.put(TbAnnotation.COLUMN_AUTHOR, annotation.getAuthor());
        cv.put(TbAnnotation.COLUMN_USERBOOKID, annotation.getUserBookId());
        long test = db.update(TbAnnotation.TABLE_NAME, cv, TbAnnotation.COLUMN_ID+"=?", new String[]{String.valueOf(annotation.getId())});
    }

    public void deleteAnnotation(String _id){
        SQLiteDatabase db = this.getWritableDatabase();
        long test = db.delete(TbAnnotation.TABLE_NAME, TbAnnotation.COLUMN_ID+"=?", new String[]{_id});
    }


}