package com.example.velev.fragmentsresponsiveui.startup;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.velev.fragmentsresponsiveui.R;
import com.example.velev.fragmentsresponsiveui.views.itemDetails.ItemDetailsFragment;
import com.example.velev.fragmentsresponsiveui.views.items.ItemsFragment;

public class StartupActivity extends AppCompatActivity {

    private static final int SCREEN_WIDTH = 600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_startup);

        Configuration config = getResources().getConfiguration();

        if (config.smallestScreenWidthDp >= SCREEN_WIDTH) {
            setContentView(R.layout.activity_startup_tablet);

            ItemsFragment itemsFragment = new ItemsFragment();
            itemsFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.items_container, itemsFragment).commit();

            ItemDetailsFragment itemDetailsFragment = new ItemDetailsFragment();
            itemDetailsFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.details_container, itemDetailsFragment).commit();

        } else {
            setContentView(R.layout.activity_startup);
            if (findViewById(R.id.container) != null) {
                if (savedInstanceState != null) {
                    return;
                }

                ItemsFragment itemsFragment = new ItemsFragment();
                itemsFragment.setArguments(getIntent().getExtras());

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, itemsFragment).commit();
            }
        }
    }
}