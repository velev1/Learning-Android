package com.example.velev.phonebook.views.tabs.contacts;

import android.content.Context;

import com.example.velev.phonebook.BaseView;
import com.example.velev.phonebook.data.models.PhoneContact;

import java.util.List;

import io.reactivex.Observable;

public interface ContactsContract {

    public interface View extends BaseView<ContactsContract.Presenter>{

        void openAddContactActivity();

        void openDetailsActivity();
    }

    public interface Presenter {

        ContactsContract.View getView();

        Observable<List<PhoneContact>> getItems(Context context);

        Observable<List<PhoneContact>> getFilteredContacts(String textToFind, Context context);
    }
}
