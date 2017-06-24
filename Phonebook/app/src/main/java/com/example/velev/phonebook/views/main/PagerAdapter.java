package com.example.velev.phonebook.views.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.velev.phonebook.views.tabs.contacts.TabContacts;
import com.example.velev.phonebook.views.tabs.dialer.TabDialer;
import com.example.velev.phonebook.views.tabs.groups.TabGroups;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new TabDialer();
            case 1:
                return new TabContacts();
            case 2:
                return new TabGroups();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
