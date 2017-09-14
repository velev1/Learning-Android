package com.example.velev.parcelabledemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReadStudentInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_student_info);

        Student student = getIntent().getParcelableExtra("student");

        TextView tvFirstName = (TextView) findViewById(R.id.tv_first_name);
        tvFirstName.setText(student.getFirstName());

        TextView tvLastName = (TextView) findViewById(R.id.tv_last_name);
        tvLastName.setText(student.getLastName());

        TextView facNumber = (TextView) findViewById(R.id.tv_fac_number);
        facNumber.setText(student.getFacNumber());
    }
}
