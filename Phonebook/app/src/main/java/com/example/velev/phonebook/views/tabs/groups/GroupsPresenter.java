package com.example.velev.phonebook.views.tabs.groups;


import android.content.Context;
import android.database.Cursor;

import com.example.velev.phonebook.data.GroupsDataProvider;
import com.example.velev.phonebook.data.models.GroupModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

public class GroupsPresenter implements GroupsContract.Presenter{
    private static final int COL_ID_INDEX = 0;
    private static final int COL_NAME_INDEX = 1;
    private final GroupsContract.View view;

    private GroupsDataProvider groupsData;

    @Inject
    public GroupsPresenter(GroupsContract.View view) {
        this.view = view;
        this.getView().setPresenter(this);
    }

    @Override
    public GroupsContract.View getView() {
        return this.view;
    }

    public Observable<List<GroupModel>> getAllGroups(Context context) {
        final Context mContext = context;
        return Observable.create(new ObservableOnSubscribe<List<GroupModel>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<GroupModel>> e) throws Exception {

                List<GroupModel> groups = new ArrayList<>();
                GroupsPresenter.this.groupsData = new GroupsDataProvider(mContext);

                Cursor result = GroupsPresenter.this.groupsData.getAllGroups();

                while (result.moveToNext()) {

                    String id = result.getString(COL_ID_INDEX);
                    String name = result.getString(COL_NAME_INDEX);

                    GroupModel groupModel = new GroupModel(id, name);

                    groups.add(groupModel);
                    e.onNext(groups);
                }
                result.close();

                e.onComplete();
            }
        });
    }
}
