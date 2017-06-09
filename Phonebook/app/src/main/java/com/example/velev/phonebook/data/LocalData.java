package com.example.velev.phonebook.data;

import com.example.velev.phonebook.data.models.PhoneContact;

import java.util.ArrayList;
import java.util.List;


public class LocalData {

    //private ArrayList<PhoneContact> contacts;

    public List<PhoneContact> getContacts() {
        return this.testData();
    }

//    public void setContacts(ArrayList<PhoneContact> contacts) {
//        this.contacts = contacts;
//    }

    private List<PhoneContact> testData() {

        List<PhoneContact> items = new ArrayList<>();

        PhoneContact p1 = new PhoneContact("Pesho", "454545");
        PhoneContact p2 = new PhoneContact("Gosho", "454545");

        items.add(p1);
        items.add(p2);
        items.add(p1);
        items.add(p2);
        items.add(p1);
        items.add(p2);
        items.add(p1);
        items.add(p2);
        items.add(p1);
        items.add(p2);
        items.add(p1);
        items.add(p2);
        items.add(p1);
        items.add(p2);
        items.add(p1);
        items.add(p2);
        items.add(p1);
        items.add(p2);

        return items;
    }
}
