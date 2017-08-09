package com.example.velev.fragmentsresponsiveui.startup;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

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

        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        this.mDevice = Device.getInstance();

        if (savedInstanceState == null) {

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

                this.mDevice.setDeviceType(DeviceType.TABLET);
            }

            // if phone landscape
            View containerLandPhone = findViewById(R.id.details_container_phone);
            if(containerLandPhone != null) {
                ItemDetailsFragment itemDetailsFragment = new ItemDetailsFragment();
                itemDetailsFragment.setArguments(getIntent().getExtras());

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.details_container_phone, itemDetailsFragment).commit();
                this.mDevice.setDeviceType(DeviceType.PHONE_LAND);
            }

            // if phone portrait
            if(container == null && containerLandPhone == null) {
                this.mDevice.setDeviceType(DeviceType.PHONE_PORT);
            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // TODO when the instance is destroyed (the field)
        this.mDevice = Device.getInstance();

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "land", Toast.LENGTH_SHORT).show();

            if (mDevice.getDeviceType() == DeviceType.PHONE_PORT) {
                mDevice.setDeviceType(DeviceType.PHONE_LAND);
            }

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "port", Toast.LENGTH_SHORT).show();

            if (mDevice.getDeviceType() == DeviceType.PHONE_LAND) {
                mDevice.setDeviceType(DeviceType.PHONE_PORT);
            }
        }
    }
}