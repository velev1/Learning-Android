package com.example.velev.phonebook.data.models;

public class GroupModel {

    private String id;
    private String name;


    public GroupModel(String name) {
        this.setName(name);
    }

    public GroupModel(String name, String id) {
        this.setName(name);
        this.setId(id);
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
