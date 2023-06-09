package com.example.appleitour.Database;

public class TbAnnotation {
    public static final String TABLE_NAME = "tbAnnotation";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_BOOKID= "_BookId";
    public static final String COLUMN_USERID= "_UserId";
    public static final String COLUMN_ANNOTATION = "_annotation";
    public static final String COLUMN_AUTHOR = "_author";
    public static final String COLUMN_BOOK = "_book";

    public static final String QUERY = "CREATE TABLE " + TABLE_NAME + "(" +
            "   " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            "   " + COLUMN_BOOKID + " INTEGER," +
         //   "    FOREIGN KEY ("+ COLUMN_BOOKID +") REFERENCES " + TbUser.TABLE_NAME +" ("+ TbBook.COLUMN_ID +")," +
            "   " + COLUMN_USERID + " INTEGER," +
         //   "    FOREIGN KEY ("+ COLUMN_USERID +") REFERENCES " + TbUser.TABLE_NAME +" ("+ TbUser.COLUMN_ID +")," +
            "   " + COLUMN_ANNOTATION + " TEXT," +
            "   " + COLUMN_AUTHOR + " TEXT," +
            "   " + COLUMN_BOOK + " TEXT)";

}
