package com.example.velev.eventreminder.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.velev.eventreminder.data.models.Event;
import com.example.velev.eventreminder.views.appStart.MainActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventsDataProvider implements EventDataContract {

    private static final String TABLE_EVENTS = "tbl_Events";

    private static final String COL_NAME = "NAME";
    private static final String COL_DATE = "DATE";
    private static final String COL_DESCRIPTION = "DESCRIPTION";

    private static final int COL_ID_INDEX = 0;
    private static final int COL_NAME_INDEX = 1;
    private static final int COL_DATE_INDEX = 2;
    private static final int COL_DESCRIPTION_INDEX = 3;

    private EventsDataHelper helper;
    private List<Event> data;

    public EventsDataProvider(Context context) {
        this.helper = new EventsDataHelper(context);
        this.data = new ArrayList<>();
    }

    @Override
    public List<Event> getAllEvents() throws ParseException {

        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor result = db.rawQuery("select * from " + TABLE_EVENTS, null);

        this.data = new ArrayList<>();

        while (result.moveToNext()) {
            String id =result.getString(COL_ID_INDEX);
            String name = result.getString(COL_NAME_INDEX);
            String strDate = result.getString(COL_DATE_INDEX);
            String description = result.getString(COL_DESCRIPTION_INDEX);

            DateFormat dateFormat;
            dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");

            Event event = new Event(id,name,dateFormat.parse(strDate), description);
            this.data.add(event);
        }

        return this.data;
    }

    @Override
    public boolean insertEvent(Event event) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_NAME, event.getName());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        String strDate = dateFormat.format(event.getDate());
        contentValues.put(COL_DATE, strDate);

        contentValues.put(COL_DESCRIPTION, event.getDescription());

        long result = db.insert(TABLE_EVENTS, null, contentValues);

        if (result == -1) {
            return false;
        }

        return true;
    }


    @Override
    public void deleteEvent(String id) {

    }

    @Override
    public void editEvent(Event event) {

    }

}
