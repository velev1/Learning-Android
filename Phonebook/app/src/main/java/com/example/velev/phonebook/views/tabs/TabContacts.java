package com.example.velev.phonebook.views.tabs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.velev.phonebook.R;
import com.example.velev.phonebook.data.LocalData;
import com.example.velev.phonebook.data.models.PhoneContact;
import com.example.velev.phonebook.views.addContact.AddContact;
import com.example.velev.phonebook.views.details.DetailsContact;
import com.example.velev.phonebook.views.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class TabContacts extends Fragment {


    private View view;
    private ListView list_view;
    private List<PhoneContact> contacts;
    private ContactsPresenter presenter;
    private ImageButton btnOpenAddContactActivity;
    private static final String CONTACT_KEY = "contact_key";
    private static ContactsAdapter adapter;
    private static Context context;


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

        this.presenter = new ContactsPresenter();
        context = this.view.getContext();
        this.contacts = this.presenter.getItems(context);

        adapter = new ContactsAdapter(getActivity(), R.layout.fragment_list_view_item, this.contacts);

        list_view = (ListView) this.view.findViewById(R.id.contacts_list);
        list_view.setAdapter(adapter);

        openDetailsActivity();

        btnOpenAddContactActivity = (ImageButton) this.view.findViewById(R.id.btn_open_add_contact_activity);
        openAddContactActivity();

        return this.view;
    }

    public void refreshView() {
        this.presenter = new ContactsPresenter();
        this.contacts = this.presenter.getItems(context);
        adapter.clear();
        adapter.addAll(this.contacts);
        adapter.notifyDataSetChanged();
    }

    private void openAddContactActivity() {
        btnOpenAddContactActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddContact.class);
                startActivity(intent);
            }
        });
    }

    private void openDetailsActivity() {
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhoneContact currentContact = (PhoneContact)list_view.getAdapter().getItem(position);
                String[] objValues = {
                        currentContact.getId(),
                        currentContact.getName(),
                        currentContact.getPhoneNumber()
                };
                //Toast.makeText(getContext(), bla, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), DetailsContact.class);
                intent.putExtra(CONTACT_KEY, objValues);
                startActivity(intent);
            }
        });
    }
}
