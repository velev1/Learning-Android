package com.example.velev.parcelabledemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        final EditText etFirstName = (EditText) findViewById(R.id.et_first_name);
        final EditText etLastName = (EditText) findViewById(R.id.et_last_name);
        final EditText etFacNumber = (EditText) findViewById(R.id.et_fac_numebr);
        Button btnOpenReadMode = (Button) findViewById(R.id.btn_open_view_mode);

        btnOpenReadMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String facNumber = etFacNumber.getText().toString();

                Student student = new Student(firstName, lastName, facNumber);

                Intent intent = new Intent(StartupActivity.this, ReadStudentInfoActivity.class);
                intent.putExtra("student", student);
                startActivity(intent);
            }
        });

    }
}
