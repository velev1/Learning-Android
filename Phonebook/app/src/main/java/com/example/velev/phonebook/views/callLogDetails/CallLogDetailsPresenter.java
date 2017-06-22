package com.example.velev.phonebook.views.callLogDetails;

public class CallLogDetailsPresenter {

    public String parseCallDuration(String duration) {

        String result = "";
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        int totalTimeInSeconds = Integer.parseInt(duration);

        seconds = totalTimeInSeconds % 60;
        minutes = (totalTimeInSeconds - seconds) / 60;
        hours = (minutes - (minutes % 60)) / 60;

        if (hours > 0) {
            String strHours = hours == 1 ? " hour " : " hours ";
            result = String.valueOf(hours) + strHours;
        }

        if (minutes > 0) {
            String strMin = minutes == 1 ? " minute " : " minutes ";
            result += String.valueOf(minutes) + strMin;
        }

        result += String.valueOf(seconds) + " sec. ";

        return result;
    }
}
