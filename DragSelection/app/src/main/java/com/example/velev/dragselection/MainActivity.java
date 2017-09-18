package com.example.velev.dragselection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.velev.dragselection.gridview.GridFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            GridFragment gridFragment = new GridFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_holder, gridFragment).commit();
        }
    }

}
