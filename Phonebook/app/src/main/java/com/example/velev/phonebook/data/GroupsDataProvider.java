package com.example.velev.phonebook.data;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GroupsDataProvider {
    private static final String TABLE_GROUPS = "tbl_groups";
    private DataProvider data;

    public GroupsDataProvider(Context context) {
        this.data = new DataProvider(context);
    }

    public Cursor getAllGroups() {

        SQLiteDatabase db = data.getWritableDatabase();

        Cursor result = db.rawQuery("select * from " + TABLE_GROUPS, null);

        return result;
    }
}
