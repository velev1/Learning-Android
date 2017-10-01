package com.example.velev.swipepartial.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 30.9.2017 г..
 */

public class DataItemProvider {

    public List<ItemModel> getData() {
        List<ItemModel> data = new ArrayList<>();

        for (int i = 0; i < 46; i++) {

            if (i != 2 ) {
                ItemModel item = new ItemModel("Item" + i, false);
                data.add(item);
            } else {
                ItemModel item = new ItemModel("Item" + i, true);
                data.add(item);
            }
        }

        return data;
    }

}
