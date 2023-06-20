package com.example.appleitour.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class Book implements Serializable {
    private String key;
    private String isbn;
    private String name;
    private String author;
    private String publisher;
    private int pages;
    private int edition;
    private String cover;
    private String sinopse;
    private String language;
    private String year;

    public Book(){}
    public Book(String _key, String _isbn,String _name,String _author,String _publisher,int _pages,int _edition,String _cover,String _sinopse,String _language,String _year) {
        this.key = _key;
        this.isbn = _isbn;
        this.name = _name;
        this.author = _author;
        this.publisher = _publisher;
        this.pages = _pages;
        this.edition = _edition;
        this.cover = _cover;
        this.sinopse = _sinopse;
        this.language = _language;
        this.year = _year;
    }


    public void setKey(String _key){this.key = _key;}
    public String getKey(){return this.key;}
    public void setIsbn(String _isbn){this.isbn = _isbn;}
    public String getIsbn(){return this.isbn;}

    public void setName(String name){this.name = name;}
    public String getName(){return this.name;}

    public void setAuthor(String author){this.author = author;}
    public String getAuthor(){return this.author;}

    public void setPublisher(String publisher){this.publisher = publisher;}
    public String getPublisher(){return this.publisher;}

    public void setPages(int pages){this.pages = pages;}
    public int getPages(){return this.pages;}

    public void setEdition(int edition){this.edition = edition;}
    public int getEdition(){return this.edition;}

    public void setCover(String cover){
        this.cover = cover;
    }
    public String getCover(){
        return this.cover;
    }

    public void setSinopse(String sinopse){this.sinopse = sinopse;}
    public String getSinopse(){return this.sinopse;}

    public void setLanguage(String language){this.language = language;}
    public String getLanguage(){return this.language;}

    public void setYear(String year){this.year = year;}
    public String getYear(){return this.year;}

}