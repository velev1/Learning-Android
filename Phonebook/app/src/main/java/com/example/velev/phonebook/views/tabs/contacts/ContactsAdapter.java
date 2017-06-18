package com.example.velev.phonebook.views.tabs.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.velev.phonebook.R;
import com.example.velev.phonebook.data.models.PhoneContact;

import java.util.List;


public class ContactsAdapter extends ArrayAdapter<PhoneContact> {

    private Context context;
    private int resource;
    private View view;
    private Holder holder;
    private List<PhoneContact> contacts;

    public ContactsAdapter(Context context, int resource, List<PhoneContact> contacts) {
        super(context, resource, contacts);

        this.context = context;
        this.resource = resource;
        this.contacts = contacts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(resource, parent, false);

        holder = new Holder();
        holder.name = (TextView) view.findViewById(R.id.tv_name);
        holder.phoneNumber = (TextView) view.findViewById(R.id.tv_phone_number);


        holder.name.setText(contacts.get(position).getName());
        holder.phoneNumber.setText(contacts.get(position).getPhoneNumber());

        return view;
    }

    private class Holder {
        TextView name;
        TextView phoneNumber;
    }
}
