package com.example.velev.rwclicklistenerexercise.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 16.8.2017 г..
 */

public class FakeData {


    private List<String> items;

    public FakeData() {
        this.items = new ArrayList<>();
        fillData();
    }

    public List<String> getItems() {
        return items;
    }

    private void fillData() {
        for (int i = 0; i < 46; i++) {
            this.items.add("Item " + i);
        }
    }
}
