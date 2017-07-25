package com.example.velev.eventreminder.views.createEventView;

import android.content.Context;

import com.example.velev.eventreminder.data.EventsDataProvider;
import com.example.velev.eventreminder.data.models.Event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CreateEventViewModel implements CreateEventContract.ViewModel {

    private Context context;

    public CreateEventViewModel(Context context) {
        this.context = context;
    }

    @Override
    public boolean insertEvent(String name, String dateTime, String description ) throws ParseException {

        EventsDataProvider data = new EventsDataProvider(this.context);

        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");

        Event event = new Event(name, dateFormat.parse(dateTime), description);

        boolean isInserted = data.insertEvent(event);

        return isInserted;
    }
}
