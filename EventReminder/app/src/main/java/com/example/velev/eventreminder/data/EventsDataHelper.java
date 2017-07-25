package com.example.velev.eventreminder.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class EventsDataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Events.db";
    private static final String TABLE_EVENTS = "tbl_Events";

    private static final String COL_ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COL_DATE = "DATE";
    private static final String COL_DESCRIPTION = "DESCRIPTION";


    public EventsDataHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_EVENTS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_DATE + " TEXT, " +
                COL_DESCRIPTION + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        onCreate(db);
    }
}
