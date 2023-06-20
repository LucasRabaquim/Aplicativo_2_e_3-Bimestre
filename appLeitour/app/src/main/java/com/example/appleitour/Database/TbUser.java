package com.example.appleitour.Database;

interface TbUser {
    
    // Interface com os campos e query de criação da tabela TbUser
    
    String TABLE_NAME = "tbUser";
    String COLUMN_ID = "_id";
    String COLUMN_NAME = "_name";
    String COLUMN_EMAIL = "_email";
    String COLUMN_PASSWORD = "_password";
    String COLUMN_PHOTO = "_photo";
    String COLUMN_COLOR = "_color";

    String  QUERY = "CREATE TABLE " + TABLE_NAME + "(" +
            "   " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "   " + COLUMN_NAME + " TEXT, " +
            "   " + COLUMN_EMAIL + " TEXT UNIQUE, " +
            "   " + COLUMN_PASSWORD + " TEXT, " +
            "   " + COLUMN_PHOTO + " BLOB, " +
            "   " + COLUMN_COLOR + " INTEGER);" ;
}
