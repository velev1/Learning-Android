package com.example.velev.fragmentsresponsiveui.views.items;

import com.example.velev.fragmentsresponsiveui.data.models.Item;

import java.util.List;

/**
 * Created by velev on 7.8.2017 г..
 */

public interface ItemsContract {

    interface View {

    }

    interface ViewModel {

        List<Item> getAllItems();
    }
}
