package com.example.velev.uitraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FragmentDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_demo);

        if(findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            ExampleFragment mFragment = new ExampleFragment();
            mFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, mFragment).commit();

        }
    }
}
