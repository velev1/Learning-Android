package com.example.velev.swipedemo.data;

import com.example.velev.swipedemo.data.models.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 25.7.2017 г..
 */

public class LocalData {

    private List<Student> students;

    public LocalData(){
        this.students = new ArrayList<>();
        fillFakeData();
    }

    public List<Student> getAllStudent(){
        return this.students;
    }

    public void deleteStudent(Student student, int position) {
        if(this.students.size() > 0) {
            this.students.remove(position);
        }
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }



    private void fillFakeData(){
        for (int i = 0; i < 25; i++) {
            String[] names = nameGenerator().split(" ");
            String firstName = names[0];
            String lastName = names[1];
            Student student = new Student(firstName, lastName, String.valueOf(i));
            this.students.add(student);
        }
    }

    private String nameGenerator() {
        String[] names = {"Georgi Zahariev", "Vlado Todorov", "Cveti Pencheva", "Dimityr grigorov", "Iliya Velev",
        "Todor Todorov", "Dimo Dimov", "Nikola Iliev", "Maria Zlateva", "Ivaylo Zlatkov", "Gergana Ivanova", "Neli Georgieva"};
        int nameIndex = (int)(Math.random() * names.length);

        return names[nameIndex];
    }
}
