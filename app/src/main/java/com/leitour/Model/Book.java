package com.leitour.Model;

import android.widget.Switch;

import com.leitour.Database.DatabaseHelper;

public class Book {
    private int isbn;
    private String name;
    private String author;
    private int pages;
    private int edition;
    private byte cover;
    private String sinopse;
    private String language;
    private String year;

    public Book(){}
    public Book(int _isbn,String _name,String _author,int _pages,int _edition,byte _cover,String _sinopse,String _language,String _year) {
        this.isbn = _isbn;
        this.name = _name;
        this.author = _author;
        this.pages = _pages;
        this.edition = _edition;
        this.cover = _cover;
        this.sinopse = _sinopse;
        this.language = _language;
        this.year = _year;
    }


    public void setIsbn(int isbn){this.isbn = isbn;}
    public int getIsbn(){return this.isbn;}

    public void setName(String name){this.name = name;}
    public String getName(){return this.name;}

    public void setAuthor(String author){this.author = author;}
    public String getAuthor(){return this.author;}

    public void setPages(int pages){this.pages = pages;}
    public int getPages(){return this.pages;}

    public void setEdition(int edition){this.edition = edition;}
    public int getEdition(){return this.edition;}

    public void setCover(byte cover){ this.cover = cover;}
    public byte getCover(){return this.cover;}

    public void setSinopse(String sinopse){this.sinopse = sinopse;}
    public String getSinopse(){return this.sinopse;}

    public void setLanguage(String language){this.language = language;}
    public String getLanguage(){return this.language;}

    public void setYear(String year){this.year = year;}
    public String getYear(){return this.year;}

}
