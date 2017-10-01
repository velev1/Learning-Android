package com.example.velev.swipepartial.data;

/**
 * Created by velev on 30.9.2017 г..
 */

public class ItemModel {

    private String name;
    private boolean isReadOnly;

    public ItemModel(String name, boolean isReadOnly) {
        this.name = name;
        this.isReadOnly = isReadOnly;
    }

    public String getName() {
        return name;
    }

    public boolean isReadOnly() {
        return isReadOnly;
    }
}
