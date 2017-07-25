package com.example.velev.eventreminder.data;

import com.example.velev.eventreminder.data.models.Event;

import java.text.ParseException;
import java.util.List;


public interface EventDataContract {

    List<Event> getAllEvents() throws ParseException;

    boolean insertEvent(Event event);

    void deleteEvent(String id);

    void editEvent(Event event);
}
