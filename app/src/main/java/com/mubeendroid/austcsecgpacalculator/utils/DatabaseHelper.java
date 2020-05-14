package com.mubeendroid.austcsecgpacalculator.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    /*
        This class saves calculated gpa in a sqlite database.
    */

    private static final String DATABASE_NAME = "aust.db";
    private static final String TABLE_NAME = "cgpa";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "YEAR";
    private static final String COL_3 = "SEMESTER";
    private static final String COL_4 = "GPA";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,YEAR INTEGER,SEMESTER INTEGER,GPA DOUBLE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertData(int year, int semester, double gpa) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where year=" + year + " and semester=" + semester, null);
        ContentValues values = new ContentValues();
        values.put(COL_2, year);
        values.put(COL_3, semester);
        values.put(COL_4, gpa);
        if (res.getCount() != 0) {
            res.moveToNext();
            db.update(TABLE_NAME, values, COL_1 + "=" + res.getInt(0), null);
        } else {
            db.insert(TABLE_NAME, null, values);
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " order by year,semester", null);
        return res;
    }

    public void deleteData(int year, int semester) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_2 + "=" + year + " and " + COL_3 + "=" + semester, null);
    }
}
