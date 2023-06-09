package com.example.appleitour.Model;

public class User {
    private int id;
    private String name;
    private String password;
    private String email;
    private byte photo;
    private int color;

    public User(){}

    public User(String name, String password, String email, byte photo, int color){
        this.name = name;
        this.password = password;
        this.email = email ;
        this.photo = photo;
        this.color = color;
    }

    public int getId() {return id;}
    public String getName(){return name;}
    public String getPassword(){return password;}
    public String getEmail(){return email;}
    public Byte getPhoto(){return photo;}
    public int getColor() {return color;}
}
