package com.example.velev.phonebook;

import android.app.Application;

import com.example.velev.phonebook.views.tabs.contacts.ContactsModule;
import com.example.velev.phonebook.views.tabs.contacts.TabContacts;

import dagger.Component;

public class PhoneBookApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate(){
        super.onCreate();

        this.component = DaggerPhoneBookApplication_ApplicationComponent
                .builder()
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    @Component(modules = {ContactsModule.class})
    public interface ApplicationComponent {
        void inject(TabContacts tabContacts);
    }
}
