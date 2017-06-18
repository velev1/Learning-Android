package com.example.velev.phonebook.views.tabs.contacts;

import android.content.Context;
import android.database.Cursor;

import com.example.velev.phonebook.data.ContactsDataProvider;
import com.example.velev.phonebook.data.models.PhoneContact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContactsPresenter {

    private static final int COL_ID_INDEX = 0;
    private static final int COL_NAME_INDEX = 1;
    private static final int COL_PHONE_NUMBER_INDEX = 2;
    private ContactsDataProvider dataProvider;
    private List<PhoneContact> items;


    public List<PhoneContact> getItems(Context context) {

        this.dataProvider = new ContactsDataProvider(context);
        Cursor result = this.dataProvider.getAllContacts();

        this.items = new ArrayList<>();

        while (result.moveToNext()) {

            String id = result.getString(COL_ID_INDEX);
            String name = result.getString(COL_NAME_INDEX);
            String phoneNumber = result.getString(COL_PHONE_NUMBER_INDEX);

            PhoneContact contact = new PhoneContact(id, name, phoneNumber);
            this.items.add(contact);
        }

        this.sortContactsByName(this.items);

        return this.items;
    }

    public List<PhoneContact> getFilteredContacts(String textToFind, Context context) {
        List<PhoneContact> newList = new ArrayList<>();
        List<PhoneContact> oldList = this.getItems(context);
        String name;
        for (int i = 0; i < oldList.size(); i++) {
            name = oldList.get(i).getName().toLowerCase();
            if (name.indexOf(textToFind.toLowerCase()) > -1) {
                newList.add(oldList.get(i));
            }
        }

        return newList;
    }

    private void sortContactsByName(List<PhoneContact> items) {
        Collections.sort(items, new Comparator<PhoneContact>() {
            public int compare(PhoneContact firstContact, PhoneContact secondContact) {
                return firstContact.getName().toLowerCase().compareTo(secondContact.getName().toLowerCase());
            }
        });
    }
}