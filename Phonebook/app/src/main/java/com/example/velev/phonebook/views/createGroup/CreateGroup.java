package com.example.velev.phonebook.views.createGroup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.velev.phonebook.R;
import com.example.velev.phonebook.views.main.MainActivity;

public class CreateGroup extends AppCompatActivity {

    private Button btnCreateGroup;
    private CreateGroupPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        this.presenter = new CreateGroupPresenter();

        this.btnCreateGroup = (Button) this.findViewById(R.id.btn_create_group);

        createGroup();

    }

    private void createGroup() {
        this.btnCreateGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((EditText)CreateGroup.this.findViewById(R.id.et_name)).getText().toString();

                boolean isInserted;
                isInserted = CreateGroup.this.presenter.insertGroup(CreateGroup.this, name);

                if(isInserted) {
                    Toast.makeText(CreateGroup.this, "Group Created", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CreateGroup.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(CreateGroup.this, "Creation NOT successful", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
