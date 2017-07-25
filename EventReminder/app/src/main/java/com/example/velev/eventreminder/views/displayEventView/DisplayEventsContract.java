package com.example.velev.eventreminder.views.displayEventView;

import android.content.Context;

import com.example.velev.eventreminder.data.models.Event;

import java.util.List;

public interface DisplayEventsContract {

    interface View {

        void deleteEventAt(int position);

        void openCreateEventView();

        void openEventDetailsView();
    }

    interface ViewModel {

        List<Event> getAllEvents(Context context);
    }
}
