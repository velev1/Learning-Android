package com.example.velev.fragmentsresponsiveui.startup;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.velev.fragmentsresponsiveui.R;
import com.example.velev.fragmentsresponsiveui.startup.enums.DeviceType;
import com.example.velev.fragmentsresponsiveui.startup.models.Device;
import com.example.velev.fragmentsresponsiveui.startup.utils.OrientationModeProvider;
import com.example.velev.fragmentsresponsiveui.views.itemDetails.ItemDetailsFragment;
import com.example.velev.fragmentsresponsiveui.views.items.ItemsFragment;

import java.util.List;

public class StartupActivity extends AppCompatActivity {

    // singleton instances
    private Device mDevice;
    private OrientationModeProvider orientationProvider;

    private View containerLandPhone;
    private View container;
    private ItemsFragment itemsFragment;
    private ItemDetailsFragment itemDetailsFragment;

    private Fragment visibleFragment;
    private boolean isLandscapePhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.orientationProvider = OrientationModeProvider.getInstance();
        this.mDevice = Device.getInstance();

        isLandscapePhone = this.orientationProvider.getIsPhoneLandscape();

        if (isLandscapePhone) {
            savedInstanceState = null;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();

        if (savedInstanceState == null) {
            // if phone
            this.itemsFragment = new ItemsFragment();
            this.itemsFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.items_container, itemsFragment).commit();

            // if tablet (large screen)
            this.container = findViewById(R.id.details_container);
            if (this.container != null) {
                ItemDetailsFragment itemDetailsFragment = new ItemDetailsFragment();
                itemDetailsFragment.setArguments(getIntent().getExtras());

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.details_container, itemDetailsFragment).commit();

                this.mDevice.setDeviceType(DeviceType.TABLET);
            }

            // if phone landscape
            this.containerLandPhone = findViewById(R.id.details_container_phone);
            if (this.containerLandPhone != null) {
                this.itemDetailsFragment = new ItemDetailsFragment();
                this.itemDetailsFragment.setArguments(getIntent().getExtras());

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.details_container_phone, itemDetailsFragment).commit();
                this.mDevice.setDeviceType(DeviceType.PHONE_LAND);
            }

            // if phone portrait
            if (container == null && containerLandPhone == null) {
                this.mDevice.setDeviceType(DeviceType.PHONE_PORT);
            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "land", Toast.LENGTH_SHORT).show();

            this.visibleFragment = getVisibleFragment();

            if (this.visibleFragment instanceof ItemDetailsFragment) {
                // getFragmentManager().popBackStack();
                this.orientationProvider.setIsPhoneLandscape(true);
            } else {
                this.orientationProvider.setIsPhoneLandscape(false);
            }

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

    // important do not remove
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Toast.makeText(this, "SaveState", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(this, "pause", Toast.LENGTH_SHORT).show();
    }

    private Fragment getVisibleFragment() {
        FragmentManager fragmentManager = StartupActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null && fragment.isVisible()) {
                    return fragment;
                }
            }
        }
        return null;
    }
}