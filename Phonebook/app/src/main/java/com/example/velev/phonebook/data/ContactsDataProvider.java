package com.example.velev.phonebook.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.velev.phonebook.data.models.PhoneContact;

public class ContactsDataProvider extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Contacts.db";
    private static final String TABLE_CONTACTS = "tbl_contacts";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COL_PHONE_NUMBER = "PHONE_NUMBER";


    public ContactsDataProvider(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CONTACTS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_PHONE_NUMBER + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public boolean updateContact(String id, String name, String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_ID, id);
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_PHONE_NUMBER, phoneNumber);

        db.update(TABLE_CONTACTS, contentValues, "ID = ?", new String[] { id });

        return true;
    }

    public Integer deleteContact(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int rowsDeleted = 0;
        rowsDeleted = db.delete(TABLE_CONTACTS, "ID = ?", new String[] { id });

        return rowsDeleted;
    }

    public boolean insertContact(PhoneContact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, contact.getName());
        contentValues.put(COL_PHONE_NUMBER, contact.getPhoneNumber());

        long result = db.insert(TABLE_CONTACTS, null, contentValues);

        if(result == -1) {
            return false;
        }

        return true;
    }

    public Cursor getAllContacts() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery("select * from " + TABLE_CONTACTS, null);

        return result;
    }
}
