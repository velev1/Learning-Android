package com.example.velev.phonebook.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.velev.phonebook.data.models.GroupModel;

public class GroupsDataProvider {
    private static final String TABLE_GROUPS = "tbl_groups";
    private static final String COL_NAME = "NAME";
    private DataProvider data;

    public GroupsDataProvider(Context context) {
        this.data = new DataProvider(context);
    }

    public Cursor getAllGroups() {

        SQLiteDatabase db = data.getWritableDatabase();

        Cursor result = db.rawQuery("select * from " + TABLE_GROUPS, null);

        return result;
    }

    public boolean insertGroup(GroupModel contact) {
        SQLiteDatabase db = data.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, contact.getName());

        long result = db.insert(TABLE_GROUPS, null, contentValues);

        if (result == -1) {
            return false;
        }

        return true;
    }
}
