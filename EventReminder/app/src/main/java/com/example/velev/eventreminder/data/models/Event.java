package com.example.velev.eventreminder.data.models;

import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable{

    private String id;
    private String name;
    private String description;
    private Date date;

//    public Event() {
//
//    }
    //id, name, date, description
    public Event(String name, Date date, String description) {
        this.setName(name);
        this.setDescription(description);
        this.setDate(date);
    }

    public Event(String id, String name, Date date, String description) {
        this(name, date, description);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {

        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    private void setDate(Date date) {
        this.date = date;
    }
}
