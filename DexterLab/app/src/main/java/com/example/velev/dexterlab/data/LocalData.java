package com.example.velev.dexterlab.data;

import com.example.velev.dexterlab.data.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 2.8.2017 г..
 */

public class LocalData {

    private List<User> users;

    public LocalData() {
        users = new ArrayList<>();
        users.add(new User("dexter", "123456"));
    }

    public List<User> getUsers() {
        return users;
    }
}
