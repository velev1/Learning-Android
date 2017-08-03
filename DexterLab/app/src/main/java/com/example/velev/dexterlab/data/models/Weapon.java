package com.example.velev.dexterlab.data.models;

/**
 * Created by velev on 3.8.2017 г..
 */

public class Weapon {
    private int img;
    private String name;
    private String usage;
    private String schema;
    private String effectiveVersus;

    public Weapon(int img, String name, String usage, String schema, String effectiveVersus) {
        this.img = img;
        this.name = name;
        this.usage = usage;
        this.schema = schema;
        this.effectiveVersus = effectiveVersus;
    }

    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getUsage() {
        return usage;
    }

    public String getSchema() {
        return schema;
    }

    public String getEffectiveVersus() {
        return effectiveVersus;
    }
}
