package com.example.velev.phonebook.views.tabs.groups;

import android.content.Context;

import com.example.velev.phonebook.BasePresenter;
import com.example.velev.phonebook.BaseView;
import com.example.velev.phonebook.data.models.GroupModel;

import java.util.List;

import io.reactivex.Observable;

public interface GroupsContract {

    interface View extends BaseView<GroupsContract.Presenter> {

        void openCreateGroupActivity();
    }

    interface Presenter extends BasePresenter<GroupsContract.View> {

        Observable<List<GroupModel>> getAllGroups(Context context);
    }
}
