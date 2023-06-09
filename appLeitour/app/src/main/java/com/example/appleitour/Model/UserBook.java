package com.example.appleitour.Model;

public class UserBook {
    private int UserBook;
    private int bookId;
    private int userId;
    public UserBook(int bookId, int userId){
        this.bookId = bookId;
        this.userId = userId;
    }

    public int getUserBookId() { return UserBook; }
    public void setUserBookId(int userBookId){this.UserBook = userBookId;}
}
