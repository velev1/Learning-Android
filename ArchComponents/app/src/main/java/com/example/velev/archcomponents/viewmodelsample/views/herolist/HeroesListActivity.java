package com.example.velev.archcomponents.viewmodelsample.views.herolist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.velev.archcomponents.R;
import com.example.velev.archcomponents.viewmodelsample.data.models.Hero;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 15.1.2018 г..
 */

public class HeroesListActivity extends AppCompatActivity {

    private HeroesViewModel mHeroesViewModel;
    private List<Hero> mData;
    private RecyclerView mRvItems;
    private HeroAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes_list);

        mData = new ArrayList<>();

        adapter = new HeroAdapter(mData);
        mRvItems =  findViewById(R.id.rv_items);
        mRvItems.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRvItems.setLayoutManager(layoutManager);

        mRvItems.setItemAnimator(new DefaultItemAnimator());

        mHeroesViewModel = ViewModelProviders.of(this).get(HeroesViewModel.class);

        subscribe();
    }

    private void subscribe() {
        final Observer<List<Hero>> itemsObserver = new Observer<List<Hero>>() {
            @Override
            public void onChanged(@Nullable List<Hero> heroes) {
                adapter.updateData(heroes);
            }
        };

        mHeroesViewModel.getItem().observe(this, itemsObserver);
    }
}