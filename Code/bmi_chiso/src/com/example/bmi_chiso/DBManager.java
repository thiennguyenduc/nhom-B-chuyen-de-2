package com.example.bmi_chiso;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String chieuc, String cann, String ketqua, String chuandoan) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.CHIEUCAO, chieuc);
        contentValue.put(DatabaseHelper.CANNANG, cann);
        contentValue.put(DatabaseHelper.KETQUA, ketqua);
        contentValue.put(DatabaseHelper.CHUANDOAN, chuandoan);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.CHIEUCAO, DatabaseHelper.CANNANG, DatabaseHelper.KETQUA, DatabaseHelper.CHUANDOAN };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
