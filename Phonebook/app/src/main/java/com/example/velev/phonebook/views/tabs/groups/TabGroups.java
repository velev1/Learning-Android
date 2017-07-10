package com.example.velev.phonebook.views.tabs.groups;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.velev.phonebook.R;
import com.example.velev.phonebook.data.models.GroupModel;
import com.example.velev.phonebook.views.createGroup.CreateGroup;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class TabGroups extends Fragment {

    private View view;
    private GroupsPresenter presenter;
    private GroupsAdapter adapter;
    private Context context;
    private List<GroupModel> groups;
    private ListView list_view;
    private ImageButton btnOpenCreateGroupActivity;

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

        this.presenter = new GroupsPresenter();
        this.context = getActivity();
        this.groups = new ArrayList<>();
        this.adapter = new GroupsAdapter(this.context, R.layout.fragment_group_list, this.groups);

        this.btnOpenCreateGroupActivity = (ImageButton) view.findViewById(R.id.btn_open_create_group_activity);

        // get the groups
        updateAdapter();

        openCreateGroupActivity();

        // set adapter
        list_view = (ListView) this.view.findViewById(R.id.lv_groups);
        list_view.setAdapter(this.adapter);

        return this.view;
    }

    private void openCreateGroupActivity() {
        this.btnOpenCreateGroupActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateGroup.class);
                startActivity(intent);
            }
        });
    }


    private void updateAdapter() {
        this.presenter.getAllGroups(this.context)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<GroupModel>>() {
                    @Override
                    public void accept(@NonNull List<GroupModel> groupModels) throws Exception {
                        TabGroups.this.adapter.clear();
                        TabGroups.this.adapter.addAll(groupModels);
                        TabGroups.this.adapter.notifyDataSetChanged();
                    }
                });
    }

}