package com.example.velev.fragmentsresponsiveui.startup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.velev.fragmentsresponsiveui.R;
import com.example.velev.fragmentsresponsiveui.startup.enums.DeviceType;
import com.example.velev.fragmentsresponsiveui.startup.models.Device;
import com.example.velev.fragmentsresponsiveui.views.itemDetails.ItemDetailsFragment;
import com.example.velev.fragmentsresponsiveui.views.items.ItemsFragment;

public class StartupActivity extends AppCompatActivity {

    private Device mDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        // if phone
        ItemsFragment itemsFragment = new ItemsFragment();
        itemsFragment.setArguments(getIntent().getExtras());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.items_container, itemsFragment).commit();

        // if tablet (large screen)
        View container = (View) findViewById(R.id.details_container);
        if (container != null) {
            ItemDetailsFragment itemDetailsFragment = new ItemDetailsFragment();
            itemDetailsFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.details_container, itemDetailsFragment).commit();

            this.mDevice = Device.getInstance(DeviceType.TABLET);
        }

        // if phone
        this.mDevice = Device.getInstance(DeviceType.PHONE);
    }
}