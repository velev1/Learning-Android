package com.example.velev.phonebook.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DataProvider extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Contacts.db";
    private static final String TABLE_CONTACTS = "tbl_contacts";
    private static final String TABLE_GROUPS = "tbl_groups";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COL_PHONE_NUMBER = "PHONE_NUMBER";
    private static final String COL_GROUP_ID = "GROUP_ID";


    public DataProvider(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys = ON;");
        db.execSQL("create table " + TABLE_GROUPS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT)");

        db.execSQL("create table " + TABLE_CONTACTS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_PHONE_NUMBER + " TEXT, " +
                COL_GROUP_ID + " INTEGER," +
                " FOREIGN KEY (" + COL_GROUP_ID + ") " +
                " REFERENCES " + TABLE_GROUPS + "(" + COL_ID + "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS);
        onCreate(db);
    }

    public boolean updateContact(String id, String name, String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_ID, id);
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_PHONE_NUMBER, phoneNumber);

        db.update(TABLE_CONTACTS, contentValues, "ID = ?", new String[]{id});

        return true;
    }

    public Integer deleteContact(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int rowsDeleted = 0;
        rowsDeleted = db.delete(TABLE_CONTACTS, "ID = ?", new String[]{id});

        return rowsDeleted;
    }
}
