package com.example.velev.phonebook.data.models;


public class PhoneContact {

    private String id;
    private String name;
    private String phoneNumber;

    public PhoneContact() {

    }

    public PhoneContact(String name, String phoneNumber) {
        this.setName(name);
        this.setPhoneNumber(phoneNumber);
    }

    public PhoneContact(String id, String name, String phoneNumber) {
        this.setName(name);
        this.setPhoneNumber(phoneNumber);
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    private void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
