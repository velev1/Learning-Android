package com.example.velev.phonebook.data.models;


import java.util.Date;

public class CallModel {
    private String name;
    private String phoneNumber;
    private String callDateTime;
    private String duration;
    private String callType;

    public CallModel(String name, String phoneNumber, String callDateTime, String duration, String callType) {
        this.setName(name);
        this.setPhoneNumber(phoneNumber);
        this.setCallDateTime(callDateTime);
        this.setDuration(duration);
        this.setCallType(callType);
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

    public String getCallDateTime() {
        return callDateTime;
    }

    private void setCallDateTime(String callDateTime) {
        this.callDateTime = callDateTime;
    }

    public String getDuration() {
        return duration;
    }

    private void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCallType() {
        return callType;
    }

    private void setCallType(String callType) {
        this.callType = callType;
    }


}

