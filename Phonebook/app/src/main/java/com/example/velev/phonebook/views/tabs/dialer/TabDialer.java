package com.example.velev.phonebook.views.tabs.dialer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.velev.phonebook.PhoneBookApplication;
import com.example.velev.phonebook.R;
import com.example.velev.phonebook.data.models.CallModel;
import com.example.velev.phonebook.views.callLogDetails.CallLogDetails;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TabDialer extends Fragment implements DialerContract.View, CallsAdapter.ItemClickListener {

    @Inject
    public DialerContract.Presenter presenter;

    private final static String CONTACT_KEY = "contact_key";

    private View view;
    private RecyclerView rvCallLog;
    private List<CallModel> calls;
    private CallsAdapter adapter;
    private Context context;
    private ProgressBar spinner;

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

        this.inject();
        this.context = this.view.getContext();
        this.calls = new ArrayList<>();

        this.spinner = (ProgressBar) this.view.findViewById(R.id.spinner);
        spinner.setVisibility(View.VISIBLE);

        this.adapter = new CallsAdapter(this.calls, this);
        this.rvCallLog = (RecyclerView) view.findViewById(R.id.rv_call_log);
        rvCallLog.setAdapter(this.adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rvCallLog.setLayoutManager(layoutManager);


        this.presenter.getCallLog(context)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<CallModel>>() {
                    @Override
                    public void accept(@NonNull List<CallModel> callModels) throws Exception {

                        TabDialer.this.adapter.swapData(callModels);
                        spinner.setVisibility(View.GONE);
                    }
                });

        return this.view;
    }

    @Override
    public void openCallLogDetails(CallModel call) {
        Intent intent = new Intent(getActivity(), CallLogDetails.class);
        intent.putExtra(CONTACT_KEY, call);
        startActivity(intent);
    }

    @Override
    public void setPresenter(DialerContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onItemClick(CallModel call) {
        openCallLogDetails(call);
    }

    private void inject() {
        ((PhoneBookApplication) (getActivity().getApplication()))
                .getComponent()
                .inject(this);
    }
}