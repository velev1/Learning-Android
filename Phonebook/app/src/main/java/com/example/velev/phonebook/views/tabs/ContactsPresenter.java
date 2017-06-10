package com.example.velev.phonebook.views.tabs;

import android.content.Context;
import android.database.Cursor;

import com.example.velev.phonebook.data.ContactsDataProvider;
import com.example.velev.phonebook.data.LocalData;
import com.example.velev.phonebook.data.models.PhoneContact;

import java.util.ArrayList;
import java.util.List;

public class ContactsPresenter {

//    private LocalData data;
    private static final int COL_ID_INDEX = 0;
    private static final int COL_NAME_INDEX = 1;
    private static final int COL_PHONE_NUMBER_INDEX = 2;
    private ContactsDataProvider dataProvider;

    public List<PhoneContact> getItems(Context context) {

//        LocalData data = new LocalData();
//
//        List<PhoneContact> items = data.getContacts();
        this.dataProvider = new ContactsDataProvider(context);
        Cursor result = this.dataProvider.getAllContacts();

        List<PhoneContact> items = new ArrayList<>();

        while (result.moveToNext()) {

            String id = result.getString(COL_ID_INDEX);
            String name = result.getString(COL_NAME_INDEX);
            String phoneNumber = result.getString(COL_PHONE_NUMBER_INDEX);

            PhoneContact contact = new PhoneContact(id, name, phoneNumber);
            items.add(contact);
        }

        return items;
    }

}
