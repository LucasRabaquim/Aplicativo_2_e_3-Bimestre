package com.example.appleitour.Database;

public class TbUserBook {

    public static final String TABLE_NAME = "TbUserBook";
    public static final String COLUMN_ID = "_UserBookId";
    public static final String COLUMN_BOOKID = "_BookId";
    public static final String COLUMN_USERID = "_UserId";

    public static final String QUERY = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
        //       "    FOREIGN KEY ("+ COLUMN_BOOKID +") REFERENCES " + TbBook.TABLE_NAME +" ("+ TbBook.COLUMN_ID +")," +
            COLUMN_USERID + " INTEGER NOT NULL," +
      //         "    FOREIGN KEY ("+ COLUMN_USERID +") REFERENCES " + TbUser.TABLE_NAME +" ("+ TbUser.COLUMN_ID +")," +
            COLUMN_BOOKID + " TEXT NOT NULL);";

}
