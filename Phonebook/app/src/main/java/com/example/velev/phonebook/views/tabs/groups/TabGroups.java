package com.example.velev.phonebook.views.tabs.groups;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.velev.phonebook.PhoneBookApplication;
import com.example.velev.phonebook.R;
import com.example.velev.phonebook.data.models.GroupModel;
import com.example.velev.phonebook.views.createGroup.CreateGroup;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class TabGroups extends Fragment implements GroupsContract.View, GroupAdapter.ItemClickListener{

    @Inject
    public GroupsContract.Presenter presenter;

    private View view;
    //private GroupsPresenter presenter;
    private GroupAdapter adapter;
    private Context context;
    private List<GroupModel> groups;
    private RecyclerView rvGroups;
    private FloatingActionButton fabAddGroup;

    public TabGroups() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (this.view == null) {
            this.view = inflater.inflate(R.layout.fragment_tab_groups, container, false);
        } else {
            ViewGroup parent = (ViewGroup) this.view.getParent();
            parent.removeView(this.view);
        }

        this.inject();
        //this.presenter = new GroupsPresenter();
        this.context = getActivity();

        this.adapter = new GroupAdapter(this.groups, this);
        rvGroups = (RecyclerView) view.findViewById(R.id.rv_groups);
        rvGroups.setAdapter(this.adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rvGroups.setLayoutManager(layoutManager);

        fabAddGroup = (FloatingActionButton) view.findViewById(R.id.fab_add_group);

        // get the groups
        updateAdapter();

        openCreateGroupActivity();

        return this.view;
    }

    @Override
    public void openCreateGroupActivity() {
        this.fabAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateGroup.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setPresenter(GroupsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onItemClick() {
        // TODO do stuff
    }

    private void updateAdapter() {
        this.presenter.getAllGroups(this.context)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<GroupModel>>() {
                    @Override
                    public void accept(@NonNull List<GroupModel> groupModels) throws Exception {
                        TabGroups.this.adapter.swapData(groupModels);
                    }
                });
    }

    private void inject() {
        ((PhoneBookApplication)(getActivity().getApplication()))
                .getComponent()
                .inject(this);
    }
}
