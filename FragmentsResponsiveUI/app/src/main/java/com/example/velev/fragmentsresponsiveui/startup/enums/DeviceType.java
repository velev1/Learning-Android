package com.example.velev.fragmentsresponsiveui.startup.enums;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.velev.fragmentsresponsiveui.R;
import com.example.velev.fragmentsresponsiveui.data.models.Item;
import com.example.velev.fragmentsresponsiveui.views.itemDetails.ItemDetailsFragment;

/**
 * Created by velev on 8.8.2017 г..
 */

public enum DeviceType {
    PHONE_PORT {
        @Override
        public void updateUI(String itemKey, Item item, AppCompatActivity activity) {
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

            Bundle args = new Bundle();
            args.putSerializable(itemKey, item);

            ItemDetailsFragment itemDetailsFragment = new ItemDetailsFragment();
            itemDetailsFragment.setArguments(args);

            transaction.replace(R.id.items_container, itemDetailsFragment);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    },
    PHONE_LAND {
        @Override
        public void updateUI(String itemKey, Item item, AppCompatActivity activity) {
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

            Bundle args = new Bundle();
            args.putSerializable(itemKey, item);

            ItemDetailsFragment itemDetailsFragment = new ItemDetailsFragment();
            itemDetailsFragment.setArguments(args);

            transaction.replace(R.id.details_container_phone, itemDetailsFragment);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    },
    TABLET {
        @Override
        public void updateUI(String itemKey, Item item, AppCompatActivity activity) {
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

            Bundle args = new Bundle();
            args.putSerializable(itemKey, item);

            ItemDetailsFragment itemDetailsFragment = new ItemDetailsFragment();
            itemDetailsFragment.setArguments(args);

            transaction.replace(R.id.details_container, itemDetailsFragment);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    };

    public void updateUI(String itemKey, Item item, AppCompatActivity activity) {

    }
}