package com.example.velev.fragmentsresponsiveui.views.items;

import com.example.velev.fragmentsresponsiveui.data.LocalData;
import com.example.velev.fragmentsresponsiveui.data.models.Item;

import java.util.List;

/**
 * Created by velev on 7.8.2017 г..
 */

public class ItemsViewModel implements ItemsContract.ViewModel{

    @Override
    public List<Item> getAllItems() {
        LocalData data = new LocalData();
        return data.getAllItems();
    }
}
