package com.example.appleitour.Model;

public class UserBook {
    private int UserBook;
    private String bookId;
    private int userId;
    public UserBook(String bookId, int userId){
        this.bookId = bookId;
        this.userId = userId;
    }

    public int getUserBookId() { return UserBook; }
    public void setUserBookId(int userBookId){this.UserBook = userBookId;}
}
