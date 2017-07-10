package com.example.velev.phonebook.views.tabs.contacts;

import android.content.Context;
import android.database.Cursor;

import com.example.velev.phonebook.data.ContactsDataProvider;
import com.example.velev.phonebook.data.models.PhoneContact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

public class ContactsPresenter implements ContactsContract.Presenter{

    private static final int COL_ID_INDEX = 0;
    private static final int COL_NAME_INDEX = 1;
    private static final int COL_PHONE_NUMBER_INDEX = 2;
    private ContactsDataProvider contactsData;
    private List<PhoneContact> items;
    private final ContactsContract.View view;

    @Inject
    public ContactsPresenter(ContactsContract.View view) {
        // TODO inject data
        this.view = view;
        this.getView().setPresenter(this);
    }

    @Override
    public ContactsContract.View getView() {
        return this.view;
    }

    @Override
    public Observable<List<PhoneContact>> getItems(Context context) {
        final Context mContext = context;

        return Observable.create(new ObservableOnSubscribe<List<PhoneContact>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<PhoneContact>> e) throws Exception {

                ContactsPresenter.this.items = new ArrayList<>();
                ContactsPresenter.this.items = ContactsPresenter.this.getContacts(mContext);

                ContactsPresenter.this.sortContactsByName(ContactsPresenter.this.items);
                e.onNext(ContactsPresenter.this.items);

                e.onComplete();
            }
        });
    }

    @Override
    public Observable<List<PhoneContact>> getFilteredContacts(String textToFind, Context context) {
        final Context mContext = context;
        final String mText = textToFind;
        return Observable.create(new ObservableOnSubscribe<List<PhoneContact>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<PhoneContact>> e) throws Exception {
                List<PhoneContact> oldList = ContactsPresenter.this.getContacts(mContext);
                List<PhoneContact> newList = new ArrayList<>();
                String name;

                for (int i = 0; i < oldList.size(); i++) {
                    name = oldList.get(i).getName().toLowerCase();
                    if (name.indexOf(mText.toLowerCase()) > -1) {
                        newList.add(oldList.get(i));
                    }
                    e.onNext(newList);
                }

                e.onComplete();
            }
        });
    }

    private List<PhoneContact> getContacts(Context context) {
        this.contactsData = new ContactsDataProvider(context);
        Cursor result = this.contactsData.getAllContacts();

        this.items = new ArrayList<>();

        while (result.moveToNext()) {

            String id = result.getString(COL_ID_INDEX);
            String name = result.getString(COL_NAME_INDEX);
            String phoneNumber = result.getString(COL_PHONE_NUMBER_INDEX);

            PhoneContact contact = new PhoneContact(id, name, phoneNumber);
            this.items.add(contact);
        }

        this.sortContactsByName(this.items);

        result.close();

        return this.items;
    }

    private void sortContactsByName(List<PhoneContact> items) {
        Collections.sort(items, new Comparator<PhoneContact>() {
            public int compare(PhoneContact firstContact, PhoneContact secondContact) {
                return firstContact.getName().toLowerCase().compareTo(secondContact.getName().toLowerCase());
            }
        });
    }
}