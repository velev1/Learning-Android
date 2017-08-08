package com.example.velev.fragmentsresponsiveui.views.items;

import android.support.v7.app.AppCompatActivity;

import com.example.velev.fragmentsresponsiveui.data.models.Item;

import java.util.List;

/**
 * Created by velev on 7.8.2017 г..
 */

public interface ItemsContract {

    interface View {
        void updateUI(String itemKey, Item item, AppCompatActivity activity);
    }

    interface ViewModel {

        List<Item> getAllItems();
    }
}
