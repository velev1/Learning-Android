package com.example.velev.phonebook.views.tabs.contacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.velev.phonebook.PhoneBookApplication;
import com.example.velev.phonebook.R;
import com.example.velev.phonebook.data.models.PhoneContact;
import com.example.velev.phonebook.views.addContact.AddContact;
import com.example.velev.phonebook.views.details.DetailsContact;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.annotations.NonNull;


public class TabContacts extends Fragment implements ContactsContract.View{

    @Inject
    public ContactsContract.Presenter presenter;

    private static final String CONTACT_KEY = "contact_key";

    private View view;
    private ListView list_view;
    private EditText etSearch;
    private ImageButton btnOpenAddContactActivity;
    private ContactsAdapter adapter;
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
        //this.presenter = new ContactsPresenter();
        this.context = this.view.getContext();


        this.contacts = new ArrayList<>();
        this.adapter = new ContactsAdapter(getActivity(), R.layout.fragment_list_view_item, this.contacts);

        updateAdapter();

        list_view = (ListView) this.view.findViewById(R.id.contacts_list);
        list_view.setAdapter(this.adapter);

        openDetailsActivity();

        btnOpenAddContactActivity = (ImageButton) this.view.findViewById(R.id.btn_open_add_contact_activity);

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
        btnOpenAddContactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddContact.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void openDetailsActivity() {
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailsContact.class);
                intent.putExtra(CONTACT_KEY, (PhoneContact) list_view.getAdapter().getItem(position));
                startActivity(intent);
            }
        });
    }

    private void getFilteredAdapter(String textToFind) {
        this.presenter.getFilteredContacts(textToFind, this.context)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<PhoneContact>>() {
                    @Override
                    public void accept(@NonNull List<PhoneContact> callModels) throws Exception {
                        TabContacts.this.adapter.clear();
                        TabContacts.this.adapter.addAll(callModels);
                        TabContacts.this.adapter.notifyDataSetChanged();
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
                        TabContacts.this.adapter.clear();
                        TabContacts.this.adapter.addAll(callModels);
                        TabContacts.this.adapter.notifyDataSetChanged();
                    }
                });
    }

    private void inject() {
        ((PhoneBookApplication)(getActivity().getApplication()))
                .getComponent()
                .inject(this);
    }
}
