package com.example.appleitour.Model;

public class User {
    private int userId;
    private String nameUser;
    private String Password;
    private String Email;
    private byte photo;//= Byte.parseByte(null);
    private int Theme = 0;
    private int RoleUser = 0;
    public boolean ActiveUser = true;
    public User(){}

    public User(String Email, String Password){
        this.nameUser = "";
        this.Password = Password;
        this.Email = Email ;
        //this.photo = new Byte(null);
        this.Theme = 0;
        this.ActiveUser = true;
    }
    public User(String nameUser, String Password, String Email){
        this.nameUser = nameUser;
        this.Password = Password;
        this.Email = Email ;
        //this.photo = Byte.parseByte(null);
        this.Theme = 0;
        this.ActiveUser = true;
    }

    public User(String nameUser, String Password, String Email, byte photo, int theme, boolean activeUser){
        this.nameUser = nameUser;
        this.Password = Password;
        this.Email = Email ;
        this.photo = photo;
        this.Theme = theme;
        this.ActiveUser = activeUser;
    }

    public int getId() {return userId;}
    public String getNameUser(){return nameUser;}
    public String getPassword(){return Password;}
    public void setPassword(String Password){this.Password = Password;}
    public String getEmail(){return Email;}
    public void setEmail(String Email){this.Email = Email;}
    public Byte getPhoto(){return photo;}
    public int getTheme() {return Theme;}
}
