package com.example.velev.eventreminder.views.displayEventView;


import android.content.Context;

import com.example.velev.eventreminder.data.EventsDataProvider;
import com.example.velev.eventreminder.data.models.Event;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DisplayEventViewModel implements DisplayEventsContract.ViewModel {

    private EventsDataProvider dataProvider;
    private List<Event> data;

    @Override
    public List<Event> getAllEvents(Context context) {

        this.data = new ArrayList<>();
        this.dataProvider = new EventsDataProvider(context);

        try {
            this.data = this.dataProvider.getAllEvents();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return this.data;
    }
}
