package com.example.velev.eventreminder.views.createEventView;

import com.example.velev.eventreminder.data.models.Event;

import java.text.ParseException;

public interface CreateEventContract {

    interface  View {

        void openDisplayEventsView();

        void onInsertEvent();
    }

    interface ViewModel {

        boolean insertEvent(String name, String dateTime, String description) throws ParseException;

    }
}
