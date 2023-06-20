package com.example.appleitour.Database;

interface TbUserBook {

    // Interface com os campos e query de criação da tabela TbUserbook
    
    String TABLE_NAME = "TbUserBook";
    String COLUMN_ID = "_UserBookId";
    String COLUMN_BOOKID = "_BookId";
    String COLUMN_USERID = "_UserId";

    String QUERY = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
        //       "    FOREIGN KEY ("+ COLUMN_BOOKID +") REFERENCES " + TbBook.TABLE_NAME +" ("+ TbBook.COLUMN_ID +")," +
            COLUMN_USERID + " INTEGER NOT NULL," +
      //         "    FOREIGN KEY ("+ COLUMN_USERID +") REFERENCES " + TbUser.TABLE_NAME +" ("+ TbUser.COLUMN_ID +")," +
            COLUMN_BOOKID + " TEXT NOT NULL);";

}
