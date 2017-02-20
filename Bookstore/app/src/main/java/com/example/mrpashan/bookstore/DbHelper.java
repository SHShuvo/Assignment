package com.example.mrpashan.bookstore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mr.Pashan on 1/21/2017.
 */


public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "BookStore.db";
    public static final String TABLE_NAME = "BookStore_table";
    public static final String COL_1 = "Id";
    public static final String COL_2 = "Book_Name";
    public static final String COL_3 = "Book_Price";
    public static final String COL_4 = "Quantity";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (Id INTEGER PRIMARY KEY AUTOINCREMENT,Book_Name TEXT,Book_Price INTEGER,Quantity INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String Book_Name,String Book_Price,String Quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,Book_Name);
        contentValues.put(COL_3,Book_Price);
        contentValues.put(COL_4,Quantity);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String Id,String Book_Name,String Book_Price,String Quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,Id);
        contentValues.put(COL_2,Book_Name);
        contentValues.put(COL_3,Book_Price);
        contentValues.put(COL_4,Quantity);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { Id });
        return true;
    }

    public Integer deleteData (String Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {Id});
    }
}
