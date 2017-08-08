package com.example.velev.fragmentsresponsiveui.data;

import com.example.velev.fragmentsresponsiveui.data.models.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 7.8.2017 г..
 */

public class LocalData {
    private static final String DETAILS = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed auctor lacus quis neque pulvinar, id maximus est volutpat. Nam non felis eu ex imperdiet malesuada non vel lacus. Nullam porta odio lectus, quis lacinia ipsum lacinia nec. Vivamus ullamcorper egestas fringilla. Interdum et malesuada fames ac ante ipsum primis in faucibus.";

    private List<Item> items;

    public LocalData(){
        this.items = new ArrayList<>();
        fillFakeData();
    }

    public List<Item> getAllItems() {
        return this.items;
    }

    private void fillFakeData() {
        for (int i = 0; i < 26; i++) {
            Item item = new Item("Item " + (i + 1), DETAILS);
             this.items.add(item);
        }
    }
}
