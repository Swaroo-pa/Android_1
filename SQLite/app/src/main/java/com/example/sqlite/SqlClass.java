package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqlClass extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "StorageBase0.db";
    public static final String TABLE_NAME = "user_register";
    public static final String COL_1 = "USERNAME";
    public static final String COL_2 = "PASSWORD";


    public SqlClass(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1); // creating database
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("create table " + TABLE_NAME + "( USERNAME TEXT, PASSWORD TEXT )"); // creating table with 2 columns
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String password){
        SQLiteDatabase db = this.getWritableDatabase(); // for writing into database
        ContentValues values = new ContentValues();
        values.put(COL_1,name);
        values.put(COL_2,password);
        long result = db.insert(TABLE_NAME,null, values);
        if(result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null); //for retrieving data from the database
        return res;
    }
}
