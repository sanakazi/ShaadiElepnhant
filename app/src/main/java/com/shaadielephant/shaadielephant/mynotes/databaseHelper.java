package com.shaadielephant.shaadielephant.mynotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Callndata on 3/2/2016.
 */
public class databaseHelper extends SQLiteOpenHelper {
    public static  final String DB_NAME = "shaadi";
    public static  final int DB_VERSION = 1;

    public databaseHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Mynotes.CREATE_TABLE);
        insertData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("Drop Table if exists " + Mynotes.CREATE_TABLE);
        onCreate(db);
    }

    public static class Mynotes{
        public static  final String TABLE_NAME = "ShaadiElephant";
        public static  final String COL_ID = "_id";
        public static  final String COL_NAME = "Name";
        public static  final String COL_DESC = "Desc";
        public static  final String CREATE_TABLE = "Create Table " + TABLE_NAME + "(" + COL_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME + " Text not null," + COL_DESC + " Text not null)";
    }

    void insertData(SQLiteDatabase db)
    {
        ContentValues values = new ContentValues();
        values.put(Mynotes.COL_NAME,"Hello");
        values.put(Mynotes.COL_DESC,"How are you?");

        db.insertOrThrow(Mynotes.TABLE_NAME,null,values);
    }
}
