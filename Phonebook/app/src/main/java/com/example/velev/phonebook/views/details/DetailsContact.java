package com.example.velev.phonebook.views.details;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.velev.phonebook.R;
import com.example.velev.phonebook.data.models.PhoneContact;
import com.example.velev.phonebook.views.main.MainActivity;

public class DetailsContact extends AppCompatActivity {

    private static final String CONTACT_KEY = "contact_key";
    private TextView tvName;
    private TextView tvPhone;
    private ImageButton btnCall;
    private ImageButton btnDelete;
    private ImageButton btnEdit;
    private DetailsPresenter presenter;
    private PhoneContact currentContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_contact);

        this.currentContact = (PhoneContact) getIntent().getSerializableExtra(CONTACT_KEY);

        tvName = (TextView) this.findViewById(R.id.tv_name);
        tvName.setText(this.currentContact.getName());

        tvPhone = (TextView) this.findViewById(R.id.tv_phone_number);
        tvPhone.setText(this.currentContact.getPhoneNumber());

        btnDelete = (ImageButton) this.findViewById(R.id.btn_delete);
        this.showDeleteAlert();

        btnEdit = (ImageButton) this.findViewById(R.id.btn_edit);
        this.showEditDialog();

        btnCall = (ImageButton) this.findViewById(R.id.btn_call);
        btnCall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView num = (TextView) findViewById(R.id.tv_phone_number);
                String number = "tel:" + num.getText().toString().trim();
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
                if (ActivityCompat.checkSelfPermission(DetailsContact.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });
    }

    private void showEditDialog() {
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(DetailsContact.this);
                final View mView = getLayoutInflater().inflate(R.layout.layout_edit_dialog, null);
                builder.setView(mView);
                final AlertDialog dialog = builder.create();

                EditText etName = (EditText) mView.findViewById(R.id.et_edit_name);
                EditText etPhone = (EditText) mView.findViewById(R.id.et_edit_phone);
                etName.setText(DetailsContact.this.currentContact.getName());
                etPhone.setText(DetailsContact.this.currentContact.getPhoneNumber());

                Button btnEdit = (Button) mView.findViewById(R.id.btn_edit);
                Button btnCancel = (Button) mView.findViewById(R.id.btn_cancel);

                btnEdit.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        String id = DetailsContact.this.currentContact.getId();
                        String name = ((EditText) mView.findViewById(R.id.et_edit_name)).getText().toString();
                        String phoneNumber = ((EditText) mView.findViewById(R.id.et_edit_phone)).getText().toString();
                        edit(id, name, phoneNumber);
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    private void showDeleteAlert() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder delDialog = new AlertDialog.Builder(DetailsContact.this);
                delDialog.setMessage("Are you sure you want to delete this contact?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                delete();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = delDialog.create();
                alert.setTitle("Attention!");
                alert.show();
            }
        });
    }

    private void edit(String id, String name, String phoneNumber) {
        boolean isUpdated = false;

        this.presenter = new DetailsPresenter();
        isUpdated = presenter.updateContact(this, id, name, phoneNumber);

        if(isUpdated) {
            Toast.makeText(this, R.string.Contact_is_updated, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(DetailsContact.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void delete() {
        String id = this.currentContact.getId();
        this.presenter = new DetailsPresenter();
        int rowsDeleted = 0;
        rowsDeleted = presenter.deleteContact(this, id);
        String rows = rowsDeleted > 1 ? " contacts" : " contact";
        Toast.makeText(this, String.valueOf(rowsDeleted) + rows + " deleted", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(DetailsContact.this, MainActivity.class);
        startActivity(intent);
    }
}
