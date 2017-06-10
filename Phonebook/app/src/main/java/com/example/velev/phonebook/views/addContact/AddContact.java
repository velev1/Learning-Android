package com.example.velev.phonebook.views.addContact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.velev.phonebook.R;
import com.example.velev.phonebook.data.ContactsDataProvider;
import com.example.velev.phonebook.data.models.PhoneContact;
import com.example.velev.phonebook.views.details.DetailsContact;
import com.example.velev.phonebook.views.main.MainActivity;
import com.example.velev.phonebook.views.tabs.TabContacts;

public class AddContact extends AppCompatActivity {

    private Button btnAdd;
    private AddContactPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        btnAdd = (Button) this.findViewById(R.id.btn_add_contact);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name = ((EditText) findViewById(R.id.et_name)).getText().toString();
                String phoneNumber = ((EditText) findViewById(R.id.et_phone_number)).getText().toString();

                presenter = new AddContactPresenter();
                boolean isInserted = false;
                isInserted = presenter.insertContact(AddContact.this, name, phoneNumber);

                if(isInserted) {
                    Toast.makeText(AddContact.this, "Contact added", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddContact.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(AddContact.this, "adding NOT successful", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
