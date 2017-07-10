package com.example.velev.phonebook.views.tabs.groups;

import dagger.Module;
import dagger.Provides;

@Module
public class GroupsModule {

    @Provides
    GroupsContract.View provideView() {
        return new TabGroups();
    }

    @Provides
    GroupsContract.Presenter providePresenter(GroupsContract.View view) {
        return new GroupsPresenter(view);
    }
}
