package com.example.velev.archcomponents.viewmodelsample.data.models;

/**
 * Created by velev on 15.1.2018 г..
 */

public class Hero {
    private String name;
    private String imgURL;


    public Hero(String name, String imgURL) {
        setName(name);
        setImgURL(imgURL);
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getImgURL() {
        return this.imgURL;
    }

    private void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
