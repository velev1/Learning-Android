package com.example.velev.phonebook.data.contracts;

import android.database.Cursor;

import com.example.velev.phonebook.data.models.GroupModel;

public interface GroupsDataContract {

    Cursor getAllGroups();

    boolean insertGroup(GroupModel contact);
}
