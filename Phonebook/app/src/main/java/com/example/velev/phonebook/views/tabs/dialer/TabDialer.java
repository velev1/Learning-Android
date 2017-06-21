package com.example.velev.phonebook.views.tabs.dialer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.velev.phonebook.R;
import com.example.velev.phonebook.data.models.CallModel;
import com.example.velev.phonebook.data.models.PhoneContact;
import com.example.velev.phonebook.views.callLogDetails.CallLogDetails;
import com.example.velev.phonebook.views.details.DetailsContact;
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

    private final static String CONTACT_KEY = "contact_key";

    private View view;
    private ListView list_view;
    private List<CallModel> calls;
    private DialerPresenter presenter;
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

        this.presenter = new DialerPresenter();
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

    private void openCallLogDetails(){
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CallLogDetails.class);
                intent.putExtra(CONTACT_KEY, (CallModel)list_view.getAdapter().getItem(position));
                startActivity(intent);
            }
        });
    }
}