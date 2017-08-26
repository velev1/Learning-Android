package com.example.velev.phonebook.views.tabs.contacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.velev.phonebook.PhoneBookApplication;
import com.example.velev.phonebook.R;
import com.example.velev.phonebook.data.models.PhoneContact;
import com.example.velev.phonebook.views.addContact.AddContact;
import com.example.velev.phonebook.views.details.DetailsContact;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.annotations.NonNull;


public class TabContacts extends Fragment implements ContactsContract.View, ContactAdapter.ItemClickListener {

    @Inject
    public ContactsContract.Presenter presenter;

    private static final String CONTACT_KEY = "contact_key";

    private View view;
    private RecyclerView rvContacts;
    private EditText etSearch;
    private FloatingActionButton fabCreateContact;
    private ContactAdapter adapter;
    private Context context;
    private List<PhoneContact> contacts;

    public TabContacts() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.view == null) {
            this.view = inflater.inflate(R.layout.fragment_tab_contacts, container, false);
        } else {
            ViewGroup parent = (ViewGroup) this.view.getParent();
            parent.removeView(this.view);
        }
        this.inject();

        this.context = this.view.getContext();

        this.adapter = new ContactAdapter(this.contacts, this);
        rvContacts = (RecyclerView) view.findViewById(R.id.rv_contacts);
        rvContacts.setAdapter(this.adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rvContacts.setLayoutManager(layoutManager);

        updateAdapter();

        fabCreateContact = (FloatingActionButton) view.findViewById(R.id.fab_add_contact);

        openAddContactActivity();

        etSearch = (EditText) view.findViewById(R.id.et_search);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    String textToFind = s.toString();
                    getFilteredAdapter(textToFind);
                } else if (s.length() == 0) {
                    updateAdapter();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return this.view;
    }

    @Override
    public void setPresenter(ContactsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void openAddContactActivity() {
        fabCreateContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddContact.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void openDetailsActivity(PhoneContact contact) {
        Intent intent = new Intent(getActivity(), DetailsContact.class);
        intent.putExtra(CONTACT_KEY, contact);
        startActivity(intent);
    }

    @Override
    public void onItemClick(PhoneContact contact) {
        openDetailsActivity(contact);
    }

    private void getFilteredAdapter(String textToFind) {
        this.presenter.getFilteredContacts(textToFind, this.context)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<PhoneContact>>() {
                    @Override
                    public void accept(@NonNull List<PhoneContact> callModels) throws Exception {
                        TabContacts.this.adapter.swapData(callModels);
                    }
                });
    }

    private void updateAdapter() {
        this.presenter.getItems(this.context)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<PhoneContact>>() {
                    @Override
                    public void accept(@NonNull List<PhoneContact> callModels) throws Exception {
                        TabContacts.this.adapter.swapData(callModels);
                    }
                });
    }

    private void inject() {
        ((PhoneBookApplication) (getActivity().getApplication()))
                .getComponent()
                .inject(this);
    }
}
