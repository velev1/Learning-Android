package com.example.velev.phonebook.views.tabs.contacts;

import dagger.Module;
import dagger.Provides;

@Module
public class ContactsModule {

    @Provides
    ContactsContract.View provideView() {
        return new TabContacts();
    }

    @Provides
    ContactsContract.Presenter providePresenter(ContactsContract.View view) {
        return new ContactsPresenter(view);
    }
}
