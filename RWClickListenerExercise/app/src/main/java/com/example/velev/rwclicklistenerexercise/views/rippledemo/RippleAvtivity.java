package com.example.velev.rwclicklistenerexercise.views.rippledemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.velev.rwclicklistenerexercise.R;

public class RippleAvtivity extends AppCompatActivity {

    private static final String EXTRA_KEY = "EXTRA_KEY";


    private TextView tvItemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple_avtivity);

        Intent intent = getIntent();

        String itemName = intent.getStringExtra(EXTRA_KEY);

        tvItemName = (TextView) findViewById(R.id.tv_item_name);
        tvItemName.setText(itemName);
    }
}
