package com.example.velev.eventreminder.views.displayEventView;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import com.example.velev.eventreminder.data.EventsDataProvider;
import com.example.velev.eventreminder.data.models.Event;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class EventLoader extends AsyncTaskLoader<List<Event>> {

    private List<Event> data;
    private Context context;
    private EventsDataProvider dataProvider;

    public EventLoader(Context context){
        super(context);

        this.context = context;
        this.data = new ArrayList<>();
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public void deliverResult(List<Event> data) {
        super.deliverResult(data);
    }

    @Override
    public List<Event> loadInBackground() {
        this.dataProvider = new EventsDataProvider(context);

        try {
            this.data = this.dataProvider.getAllEvents();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return this.data;
    }
}


