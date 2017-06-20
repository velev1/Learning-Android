package com.example.velev.phonebook.views.tabs.dialer;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.velev.phonebook.R;
import com.example.velev.phonebook.data.models.CallModel;
import com.example.velev.phonebook.views.tabs.contacts.TabContacts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TabDialer extends Fragment {

    private View view;
    private ListView list_view;
    private List<CallModel> calls;
    private DialerPresenter presenter;
    private CallsAdapter adapter;
    private Context context;

    public TabDialer() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.view == null) {
            this.view = inflater.inflate(R.layout.fragment_tab_dailer, container, false);
        } else {
            ViewGroup parent = (ViewGroup) this.view.getParent();
            parent.removeView(this.view);
        }

        this.presenter = new DialerPresenter();
        this.context = this.view.getContext();

        CallModel callModel = new CallModel("","","Loading...","","");
        this.calls = new ArrayList<>();
        this.calls.add(callModel);

        this.adapter = new CallsAdapter(getActivity(), R.layout.fragment_call__log, this.calls);

        this.presenter.getCallLog(context)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<CallModel>>() {
                    @Override
                    public void accept(@NonNull List<CallModel> callModels) throws Exception {
                        TabDialer.this.adapter.clear();
                        TabDialer.this.adapter.addAll(callModels);
                        TabDialer.this.adapter.notifyDataSetChanged();
                    }
                });

        list_view = (ListView) this.view.findViewById(R.id.dialer_list);
        list_view.setAdapter(this.adapter);

        return this.view;
    }
}