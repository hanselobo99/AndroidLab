package com.example.loginapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
    DatabaseHelper databaseHelper;
    Context context;
    SQLiteDatabase database;

    DatabaseManager(Context ctx) {
        context = ctx;
    }

    public DatabaseManager open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);

        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    public long Insert(String username, String password) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(databaseHelper.USER_NAME, username);
        contentValues.put(databaseHelper.USER_PASSWORD, password);
        long id = db.insert(databaseHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public int Update(Long _id, String username, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(databaseHelper.USER_NAME, username);
        contentValues.put(databaseHelper.USER_PASSWORD, password);
        int result = database.update(databaseHelper.TABLE_NAME, contentValues, databaseHelper.USER_ID + " = " + _id, null);
        return result;
    }

    public void Delete(Long _id) {
        database.delete(databaseHelper.TABLE_NAME, databaseHelper.USER_ID + "=" + _id, null);
    }

    public Cursor Fetch(long id) {
        String[] columns = new String[]{databaseHelper.USER_ID, databaseHelper.USER_NAME, databaseHelper.USER_PASSWORD};
        Cursor cursor = database.query(databaseHelper.TABLE_NAME, columns, databaseHelper.USER_ID + " = ? ", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
