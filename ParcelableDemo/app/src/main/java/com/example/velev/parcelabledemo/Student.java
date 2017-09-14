package com.example.velev.parcelabledemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by velev on 14.9.2017 г..
 */

public class Student implements Parcelable {
    private String firstName;
    private String lastName;
    private String facNumber;

    public Student(String firstName, String lastName, String facNumber) {
        setFirstName(firstName);
        setLastName(lastName);
        setFacNumber(facNumber);
    }

    public String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFacNumber() {
        return this.facNumber;
    }

    private void setFacNumber(String facNumber) {
        this.facNumber = facNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getFirstName());
        parcel.writeString(getLastName());
        parcel.writeString(getFacNumber());
    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {

        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    /**
     * Retrieving Student data from Parcel object
     * This constructor is invoked by the method createFromParcel(Parcel source) of
     * the object CREATOR
     **/
    private Student(Parcel in) {
        setFirstName(in.readString());
        setLastName(in.readString());
        setFacNumber(in.readString());
    }
}
