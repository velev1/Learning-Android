package com.example.velev.uitraining;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class ExampleFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_example, container, false);

        Button btnDanger = (Button) view.findViewById(R.id.btn_danger);
        btnDanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "I am dangarous Button", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
