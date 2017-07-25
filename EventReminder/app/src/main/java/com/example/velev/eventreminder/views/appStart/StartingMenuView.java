package com.example.velev.eventreminder.views.appStart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.velev.eventreminder.R;
import com.example.velev.eventreminder.views.createEventView.CreateEventActivity;


public class StartingMenuView extends Fragment implements StartMenuContract.View{

    private Button btnCreateEvent;
    private View view;

    public StartingMenuView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view  = inflater.inflate(R.layout.fragment_starting_menu, container, false);


        btnCreateEvent = (Button) view.findViewById(R.id.btn_create_event);
        btnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"test", Toast.LENGTH_LONG).show();
                openCreateEventView();
            }
        });

        return this.view;
    }

    @Override
    public void openCreateEventView() {
        Intent intent = new Intent(getActivity(), CreateEventActivity.class);
        startActivity(intent);
    }

    @Override
    public void openDisplayEventsView() {

    }
}
