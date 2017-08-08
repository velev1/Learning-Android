package com.example.velev.fragmentsresponsiveui.data.models;

import java.io.Serializable;

/**
 * Created by velev on 7.8.2017 г..
 */

public class Item implements Serializable{
    private String name;
    private String details;

    public Item(String name, String details) {
        this.name = name;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }
}
