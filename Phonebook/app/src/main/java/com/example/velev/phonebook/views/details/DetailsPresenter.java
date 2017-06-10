package com.example.velev.phonebook.views.details;

import android.content.Context;

import com.example.velev.phonebook.data.ContactsDataProvider;

public class DetailsPresenter {

    private ContactsDataProvider dataProvider;

    public Integer deleteContact(Context context, String id) {
        this.dataProvider = new ContactsDataProvider(context);
        int deletedRows = dataProvider.deleteContact(id);
        return deletedRows;
    }

    public boolean updateContact(Context context, String id, String name, String phoneNumber) {
        this.dataProvider = new ContactsDataProvider(context);
        boolean isUpdated = false;
        isUpdated = dataProvider.updateContact(id, name, phoneNumber);
        return isUpdated;
    }

}
