package com.example.velev.phonebook;

import android.app.Application;

import com.example.velev.phonebook.views.details.DetailsContact;
import com.example.velev.phonebook.views.details.DetailsContactsModule;
import com.example.velev.phonebook.views.tabs.contacts.ContactsModule;
import com.example.velev.phonebook.views.tabs.contacts.TabContacts;
import com.example.velev.phonebook.views.tabs.dialer.DialerModule;
import com.example.velev.phonebook.views.tabs.dialer.TabDialer;
import com.example.velev.phonebook.views.tabs.groups.GroupsModule;
import com.example.velev.phonebook.views.tabs.groups.TabGroups;

import dagger.Component;

public class PhoneBookApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        this.component = DaggerPhoneBookApplication_ApplicationComponent
                .builder()
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    @Component(modules = {
            ContactsModule.class,
            DialerModule.class,
            GroupsModule.class,
            DetailsContactsModule.class})
    public interface ApplicationComponent {
        void inject(TabContacts tabContacts);

        void inject(TabDialer tabDialer);

        void inject(TabGroups tabGroups);

        void inject(DetailsContact detailsContact);
    }


}
