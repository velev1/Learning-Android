package com.example.velev.phonebook.views.createGroup;

import android.content.Context;

import com.example.velev.phonebook.data.GroupsDataProvider;
import com.example.velev.phonebook.data.models.GroupModel;

public class CreateGroupPresenter {

    private GroupModel groupModel;
    private GroupsDataProvider groupsData;

    public boolean insertGroup(Context context, String name) {
        this.groupModel = new GroupModel(name);
        this.groupsData = new GroupsDataProvider(context);

        if (groupsData.insertGroup(this.groupModel)) {
            return true;
        } else {
            return false;
        }
    }
}
