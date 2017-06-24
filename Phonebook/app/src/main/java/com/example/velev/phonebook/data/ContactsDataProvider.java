package com.example.velev.phonebook.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.velev.phonebook.data.models.PhoneContact;

public class ContactsDataProvider {
    private static final String TABLE_CONTACTS = "tbl_contacts";
    private static final String COL_NAME = "NAME";
    private static final String COL_PHONE_NUMBER = "PHONE_NUMBER";

    DataProvider data;

    public ContactsDataProvider(Context context) {
        this.data = new DataProvider(context);
    }

    public Cursor getAllContacts() {
        SQLiteDatabase db = data.getWritableDatabase();

        Cursor result = db.rawQuery("select * from " + TABLE_CONTACTS, null);

        return result;
    }

    public boolean insertContact(PhoneContact contact) {
        SQLiteDatabase db = data.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, contact.getName());
        contentValues.put(COL_PHONE_NUMBER, contact.getPhoneNumber());

        long result = db.insert(TABLE_CONTACTS, null, contentValues);

        if (result == -1) {
            return false;
        }

        return true;
    }
}
