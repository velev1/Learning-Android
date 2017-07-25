package com.example.velev.eventreminder.views.appStart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.velev.eventreminder.R;
import com.example.velev.eventreminder.data.EventsDataHelper;
import com.example.velev.eventreminder.views.createEventView.CreateEventActivity;
import com.example.velev.eventreminder.views.displayEventView.DisplayEventActivity;

public class MainActivity extends AppCompatActivity implements StartMenuContract.View{

    private Button btnCreateEvent;
    private Button btnDisplayEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_starting_menu);

        openCreateEventView();
        openDisplayEventsView();
    }

    @Override
    public void openCreateEventView() {
        btnCreateEvent = (Button) findViewById(R.id.btn_create_event);
        btnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateEventActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void openDisplayEventsView() {
        btnDisplayEvents = (Button) findViewById(R.id.btn_show_events);
        btnDisplayEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DisplayEventActivity.class);
                startActivity(intent);
            }
        });
    }
}
