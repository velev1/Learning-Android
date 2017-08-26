package com.example.velev.phonebook.views.tabs.contacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.velev.phonebook.R;
import com.example.velev.phonebook.data.models.PhoneContact;

import java.util.ArrayList;
import java.util.List;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactHolder> {

    private List<PhoneContact> contacts;
    private ItemClickListener listener;

    public ContactAdapter(List<PhoneContact> contacts, ItemClickListener listener) {
        if(contacts == null) {
            this.contacts = new ArrayList<>();
        } else {
            this.contacts = contacts;
        }

        this.listener = listener;
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_contacts_row, parent, false);

        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        PhoneContact contact = this.contacts.get(position);

        holder.tvName.setText(contact.getName());
        holder.tvPhoneNumber.setText(contact.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return this.contacts.size();
    }

    public void swapData(List<PhoneContact> contacts) {
        this.contacts.clear();
        this.contacts.addAll(contacts);
        notifyDataSetChanged();
    }

    interface ItemClickListener {
        void onItemClick(PhoneContact contact);
    }


    public class ContactHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener{
        private TextView tvName;
        private TextView tvPhoneNumber;

        public ContactHolder(View view) {
            super(view);

            this.tvName = (TextView) view.findViewById(R.id.tv_name);
            this.tvPhoneNumber = (TextView) view.findViewById(R.id.tv_phone_number);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int positionClicked = getAdapterPosition();
            listener.onItemClick(contacts.get(positionClicked));
        }
    }
}
