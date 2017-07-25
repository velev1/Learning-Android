package com.example.velev.swipedemo.data.models;

/**
 * Created by velev on 25.7.2017 г..
 */

public class Student {
    private String firstName;
    private String lastName;
    private String facultyNumber;

    public Student(String firstName, String lastName, String facultyNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.facultyNumber = facultyNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }
}
