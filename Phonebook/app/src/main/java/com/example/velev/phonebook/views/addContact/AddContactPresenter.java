package com.example.velev.phonebook.views.addContact;

import android.content.Context;

import com.example.velev.phonebook.data.ContactsDataProvider;
import com.example.velev.phonebook.data.models.PhoneContact;
import com.example.velev.phonebook.views.tabs.ContactsPresenter;

public class AddContactPresenter {

    private PhoneContact contact;
    private ContactsDataProvider dataProvider;
    private ContactsPresenter contactsPresenter;

    public boolean insertContact(Context context, String name, String phoneNumber) {
        this.contactsPresenter = new ContactsPresenter();
        this.contact = new PhoneContact(name, phoneNumber);

        this.dataProvider = new ContactsDataProvider(context);

        if (dataProvider.insertContact(this.contact)) {
            contactsPresenter.refreshAdapter();
            return true;
        } else {
            return false;
        }
    }
}

