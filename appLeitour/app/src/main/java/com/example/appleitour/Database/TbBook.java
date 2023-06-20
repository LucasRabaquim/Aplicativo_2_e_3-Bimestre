package com.example.appleitour.Database;

interface TbBook {

    // Interface com os campos e query de criação da tabela TbBook

    String TABLE_NAME = "tbBook";
    String COLUMN_ID = "bookKey";
    String COLUMN_ISBN = "isbn";
    String COLUMN_NAME = "name";
    String COLUMN_AUTHOR = "author";
    String COLUMN_PUBLISHER = "publisher";
    String COLUMN_PAGES = "pages";
    String COLUMN_EDITION = "edition";
    String COLUMN_COVER = "cover";
    String COLUMN_SINOPSE = "sinopse";
    String COLUMN_YEAR = "year";
    String COLUMN_LANGUAGE = "_language";

    String QUERY = "CREATE TABLE " + TABLE_NAME + "(" +
            "   " + COLUMN_ID + " TEXT PRIMARY KEY," +
            "   " + COLUMN_ISBN + " INTEGER," +
            "   " + COLUMN_NAME + " TEXT NOT NULL," +
            "   " + COLUMN_AUTHOR + " TEXT NOT NULL," +
            "   " + COLUMN_PUBLISHER + " TEXT NOT NULL," +
            "   " + COLUMN_PAGES + " INTEGER," +
            "   " + COLUMN_EDITION + " INTEGER," +
            "   " + COLUMN_COVER + " TEXT," +
            "   " + COLUMN_SINOPSE + " TEXT," +
            "   " + COLUMN_YEAR + " TEXT NOT NULL," +
            "   " + COLUMN_LANGUAGE + " TEXT);";

}
