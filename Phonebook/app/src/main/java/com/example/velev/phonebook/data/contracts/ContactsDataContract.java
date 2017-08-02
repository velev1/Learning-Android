package com.example.velev.phonebook.data.contracts;

import android.database.Cursor;

import com.example.velev.phonebook.data.models.PhoneContact;

public interface ContactsDataContract {

    Cursor getAllContacts();

    boolean insertContact(PhoneContact contact);

    Integer deleteContact(String id);

    boolean updateContact(String id, String name, String phoneNumber);
}
