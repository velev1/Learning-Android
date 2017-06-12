package com.example.velev.phonebook.views.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.velev.phonebook.views.tabs.contacts.TabContacts;
import com.example.velev.phonebook.views.tabs.dialer.TabDialer;
import com.example.velev.phonebook.views.tabs.info.TabInfo;

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
                TabDialer tabDialer = new TabDialer();
                return tabDialer;
            case 1:
                TabContacts tabContacts = new TabContacts();
                return tabContacts;
            case 2:
                TabInfo tabInfo = new TabInfo();
                return tabInfo;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
