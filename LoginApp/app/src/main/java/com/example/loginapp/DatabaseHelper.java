package com.example.loginapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "MyDEMODB.DB";
    static final int VERSION = 1;
    static final String TABLE_NAME = "USERS";
    static final String USER_ID = "_ID";
    static final String USER_NAME = "user_name";
    static final String USER_PASSWORD = "password";
    private static final String CREATE_DB_QUERY = "CREATE TABLE " + TABLE_NAME + " ( "
            + USER_ID + " INTEGER PRIMARY KEY ,"
            + USER_NAME + " TEXT ,"
            + USER_PASSWORD + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        Log.d("query", "from const " + CREATE_DB_QUERY);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB_QUERY);
        Log.d("query", CREATE_DB_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
