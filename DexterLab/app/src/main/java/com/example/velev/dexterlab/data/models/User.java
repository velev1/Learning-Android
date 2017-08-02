package com.example.velev.dexterlab.data.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 2.8.2017 г..
 */

public class User {

    private String username;
    private String password;

    public User(String username, String password) {

        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
