package com.example.appleitour.Database;
interface TbAnnotation {

    // Interface com os campos e query de criação da tabela TbAnnotation

    String TABLE_NAME = "tbAnnotation";
    String COLUMN_ID= "_Id";
    String COLUMN_USERBOOKID= "_UserBookId";
    String COLUMN_ANNOTATION = "_annotation";
    String COLUMN_AUTHOR = "_author";
    String COLUMN_BOOK = "_book";

    String QUERY = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_USERBOOKID + " INTEGER NOT NULL," +
         //   "FOREIGN KEY ("+ COLUMN_USERBOOKID +") REFERENCES " + TbUserBook.TABLE_NAME + " ("+ TbUserBook.COLUMN_ID +")," +
            COLUMN_ANNOTATION + " TEXT," +
            COLUMN_AUTHOR + " TEXT NOT NULL," +
            COLUMN_BOOK + " TEXT NOT NULL);";
}
