package com.example.velev.phonebook.views.tabs.dialer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class TabDialer extends Fragment implements DialerContract.View {

    @Inject
    public DialerContract.Presenter presenter;

    private final static String CONTACT_KEY = "contact_key";

    private View view;
    private ListView list_view;
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

        this.adapter = new CallsAdapter(getActivity(), R.layout.fragment_call__log, this.calls);

        this.presenter.getCallLog(context)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<CallModel>>() {
                    @Override
                    public void accept(@NonNull List<CallModel> callModels) throws Exception {
                        TabDialer.this.adapter.clear();
                        spinner.setVisibility(View.GONE);
                        TabDialer.this.adapter.addAll(callModels);
                        TabDialer.this.adapter.notifyDataSetChanged();
                    }
                });

        list_view = (ListView) this.view.findViewById(R.id.dialer_list);
        list_view.setAdapter(this.adapter);

        openCallLogDetails();

        return this.view;
    }

    @Override
    public void openCallLogDetails(){
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CallLogDetails.class);
                intent.putExtra(CONTACT_KEY, (CallModel)list_view.getAdapter().getItem(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void setPresenter(DialerContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private void inject() {
        ((PhoneBookApplication)(getActivity().getApplication()))
                .getComponent()
                .inject(this);
    }
}