package com.example.velev.phonebook.views.details;

import android.content.Context;

import com.example.velev.phonebook.data.ContactsDataProvider;

public class DetailsPresenter {

    private ContactsDataProvider dataProvider;

    public Integer deleteContact(Context context, String id) {
        this.dataProvider = new ContactsDataProvider(context);
        return dataProvider.deleteContact(id);
    }

    public boolean updateContact(Context context, String id, String name, String phoneNumber) {
        this.dataProvider = new ContactsDataProvider(context);
        return dataProvider.updateContact(id, name, phoneNumber);
    }

}
