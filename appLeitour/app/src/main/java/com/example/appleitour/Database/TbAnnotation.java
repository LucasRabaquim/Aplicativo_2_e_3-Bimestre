package com.example.appleitour.Database;
public class TbAnnotation {
    public static final String TABLE_NAME = "tbAnnotation";
    public static final String COLUMN_ID= "_Id";
    public static final String COLUMN_USERBOOKID= "_UserBookId";
    public static final String COLUMN_ANNOTATION = "_annotation";
    public static final String COLUMN_AUTHOR = "_author";
    public static final String COLUMN_BOOK = "_book";

    public static final String QUERY = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_USERBOOKID + " INTEGER NOT NULL," +
         //   "FOREIGN KEY ("+ COLUMN_USERBOOKID +") REFERENCES " + TbUserBook.TABLE_NAME + " ("+ TbUserBook.COLUMN_ID +")," +
            COLUMN_ANNOTATION + " TEXT," +
            COLUMN_AUTHOR + " TEXT NOT NULL," +
            COLUMN_BOOK + " TEXT NOT NULL);";
}
