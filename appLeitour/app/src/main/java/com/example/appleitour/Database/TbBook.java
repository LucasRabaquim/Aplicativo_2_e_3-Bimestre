package com.example.appleitour.Database;

public class TbBook {
    public static final String TABLE_NAME = "tbBook";
    public static final String COLUMN_ID = "isbn";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_PUBLISHER = "publisher";
    public static final String COLUMN_PAGES = "pages";
    public static final String COLUMN_EDITION = "edition";
    public static final String COLUMN_COVER = "cover";
    public static final String COLUMN_SINOPSE = "sinopse";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_LANGUAGE = "_language";

    public static final String QUERY = "CREATE TABLE " + TABLE_NAME + "(" +
            "   " + COLUMN_ID + " INTEGER PRIMARY KEY," +
            "   " + COLUMN_NAME + " TEXT NOT NULL," +
            "   " + COLUMN_AUTHOR + " TEXT NOT NULL," +
            "   " + COLUMN_PUBLISHER + " TEXT NOT NULL," +
            "   " + COLUMN_PAGES + " INTEGER NOT NULL," +
            "   " + COLUMN_EDITION + " INTEGER NOT NULL," +
            "   " + COLUMN_COVER + " BLOB," +
            "   " + COLUMN_SINOPSE + " TEXT," +
            "   " + COLUMN_YEAR + " TEXT NOT NULL," +
            "   " + COLUMN_LANGUAGE + " TEXT NOT NULL);";

}
