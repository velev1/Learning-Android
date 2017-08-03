package com.example.velev.dexterlab.views.lab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.velev.dexterlab.R;

public class LabActivity extends AppCompatActivity {

    private RecyclerView rvWeapons;
    private WeaponAdapter adapter;
    private LabViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);

        viewModel = new LabViewModel(this);
        rvWeapons = (RecyclerView) findViewById(R.id.rv_weapons);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvWeapons.setLayoutManager(layoutManager);

        rvWeapons.setItemAnimator(new DefaultItemAnimator());

        adapter = new WeaponAdapter(viewModel.getAllWeapons());
        rvWeapons.setAdapter(adapter);

    }
}
