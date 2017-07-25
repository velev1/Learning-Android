package com.example.velev.eventreminder.views.createEventView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.velev.eventreminder.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventView extends Fragment {


    public CreateEventView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_event_view, container, false);
    }

}
