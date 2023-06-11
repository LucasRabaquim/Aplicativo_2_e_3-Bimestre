package com.example.appleitour.Database;

public class TbUser {
    public static final String TABLE_NAME = "tbUser";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "_name";
    public static final String COLUMN_EMAIL = "_email";
    public static final String COLUMN_PASSWORD = "_password";
    public static final String COLUMN_PHOTO = "_photo";
    public static final String COLUMN_COLOR = "_color";
    public TbUser(){}

    public static final String  QUERY = "CREATE TABLE " + TABLE_NAME + "(" +
            "   " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "   " + COLUMN_NAME + " TEXT, " +
            "   " + COLUMN_EMAIL + " TEXT UNIQUE, " +
            "   " + COLUMN_PASSWORD + " TEXT, " +
            "   " + COLUMN_PHOTO + " BLOB, " +
            "   " + COLUMN_COLOR + " INTEGER);" ;
}
