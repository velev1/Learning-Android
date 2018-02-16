package com.example.velev.archcomponents.viewmodelsample.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by velev on 10.2.2018 г..
 */
@Entity(tableName = "heroes")
public class HeroEntity implements Serializable {
    @PrimaryKey(autoGenerate=true)
    @NonNull
    private int id;
    private String name;
    private String imgURL;

    public HeroEntity(String name, String imgURL) {
        setName(name);
        setImgURL(imgURL);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgURL() {
        return this.imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
