package com.example.velev.dexterlab.data.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 2.8.2017 г..
 */

public class User {

    private List<User> users;

    private String username;
    private String password;

    public User(String username, String password) {
        users = new ArrayList<>();
        users.add(new User("dexter", "123456"));
        this.username = username;
        this.password = password;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
