package com.example.appleitour.Model;

import java.io.Serializable;

public class Annotation implements Serializable {
    private int id;
    private int userBook;
    private String annotation;
    private String author;
    private String book;

    public Annotation(){}
    public Annotation(int _userBook, String _annotation, String _author, String _book){
        this.userBook = _userBook;
        this.annotation = _annotation;
        this.author = _author;
        this.book = _book;
    }

    public void setId(int _id){this.id = _id;}
    public int getId(){return this.id;}

    public void setUserBookId(int _userBook){this.userBook = _userBook;}
    public int getUserBookId(){return this.userBook;}
    public void setAnnotation(String _annotation){this.annotation = _annotation;}
    public String getAnnotation(){return this.annotation;}

    public void setAuthor(String _author){this.author = _author;}
    public String getAuthor(){return this.author;}

    public void setBook(String _book){this.book = _book;}
    public String getBook(){return this.book;}
}