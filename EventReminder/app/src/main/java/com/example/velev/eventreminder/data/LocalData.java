package com.example.velev.eventreminder.data;

import com.example.velev.eventreminder.data.models.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LocalData  {

    private static final String DESCRIPTION_TEST_STRING =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Morbi scelerisque laoreet eros, ut gravida turpis. " +
                    "Pellentesque nec aliquam sapien, a consectetur libero. " +
                    "Fusce vel odio rhoncus, scelerisque urna sit amet.";
    private List<Event> data;

    public LocalData() {
        this.data = new ArrayList<>();
        fillData();
    }


    public List<Event> getAllEvents() {
        return this.data;
    }


    public void createEvent() {

    }


    public void deleteEvent(String id) {

    }


    public void editEvent(Event event) {

    }

    private void fillData() {
        for (int i = 0; i < 25; i++) {
            Event event = new Event("Some Event" + " "+ String.valueOf(i), new Date(),DESCRIPTION_TEST_STRING);
            this.data.add(event);
        }
    }
}
