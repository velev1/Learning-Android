package com.example.velev.phonebook.views.addContact;

import android.content.Context;

import com.example.velev.phonebook.data.ContactsDataProvider;
import com.example.velev.phonebook.data.DataProvider;
import com.example.velev.phonebook.data.models.PhoneContact;

public class AddContactPresenter {

    private PhoneContact contact;
    private ContactsDataProvider contactsData;

    public boolean insertContact(Context context, String name, String phoneNumber) {
        this.contact = new PhoneContact(name, phoneNumber);
        this.contactsData = new ContactsDataProvider(context);

        if (contactsData.insertContact(this.contact)) {
            return true;
        } else {
            return false;
        }
    }
}

