package com.example.velev.rwclicklistenerexercise.views.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.velev.rwclicklistenerexercise.R;
import com.example.velev.rwclicklistenerexercise.data.FakeData;
import com.example.velev.rwclicklistenerexercise.views.rippledemo.RippleAvtivity;

public class StartupActivity extends AppCompatActivity
        implements ItemsAdapter.ItemClickListener{

    private static final String EXTRA_KEY = "EXTRA_KEY";

    private RecyclerView rvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        FakeData data = new FakeData();
        // what if the class implements more than one interface  is "this" word still work
        ItemsAdapter adapter = new ItemsAdapter(data.getItems(), this);
        rvItems = (RecyclerView) findViewById(R.id.rv_items);
        rvItems.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvItems.setLayoutManager(layoutManager);

        rvItems.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onItemClicked(int positionClicked, String itemName) {
        Toast.makeText(this, "position: " + positionClicked + " clocked", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(StartupActivity.this, RippleAvtivity.class);
        intent.putExtra(EXTRA_KEY, itemName);
        startActivity(intent);
    }
}
