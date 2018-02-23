package com.example.velev.archcomponents.viewmodelsample.views.herolist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.velev.archcomponents.R;
import com.example.velev.archcomponents.viewmodelsample.data.entity.HeroEntity;
import com.example.velev.archcomponents.viewmodelsample.views.herodetails.HeroDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 15.1.2018 г..
 */

public class HeroesListActivity extends AppCompatActivity implements HeroAdapter.ItemClickListener {
    private static final String HERO = "HERO";
    private HeroesViewModel mHeroesViewModel;
    private List<HeroEntity> mData;
    private RecyclerView mRvItems;
    private HeroAdapter adapter;
    private AlertDialog mDialog;

    private FloatingActionButton mFabAddHero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUi();
    }

    @Override
    public void onItemClick(HeroEntity heroEntity) {
        openDetailsActivity(heroEntity);
    }

    private void openDetailsActivity(HeroEntity heroEntity) {
        Intent intent = new Intent(this, HeroDetailsActivity.class);
        intent.putExtra(HERO, heroEntity);
        startActivity(intent);
    }

    private void initUi() {
        setContentView(R.layout.activity_heroes_list);

        mData = new ArrayList<>();

        adapter = new HeroAdapter(mData, this, getApplicationContext());
        mRvItems = findViewById(R.id.rv_items);
        mRvItems.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRvItems.setLayoutManager(layoutManager);

        mRvItems.setItemAnimator(new DefaultItemAnimator());

        mHeroesViewModel = ViewModelProviders.of(this).get(HeroesViewModel.class);

        mFabAddHero = findViewById(R.id.fab_add_hero);
        addHero();

        subscribe();
    }

    private void addHero() {
        mFabAddHero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddHeroDialog();
            }
        });
    }

    private void openAddHeroDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.add_hero_dialog, null);
        builder.setView(view);
        mDialog = builder.create();


        onSave(view);
        onCancel(view);

        mDialog.show();
    }

    private void onSave(View view) {
        final View dialogView = view;
        TextView saveHero = view.findViewById(R.id.save);
        saveHero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String heroName = ((EditText) (dialogView.findViewById(R.id.et_heroname))).getText().toString();
                final String url = ((EditText) (dialogView.findViewById(R.id.et_url))).getText().toString();
                HeroEntity hero = new HeroEntity(heroName, url);
                mHeroesViewModel.insertHero(hero);
            }
        });
    }

    private void onCancel(View view) {
        TextView cancel = view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
    }

    private void subscribe() {
        final Observer<List<HeroEntity>> itemsObserver = new Observer<List<HeroEntity>>() {
            @Override
            public void onChanged(@Nullable List<HeroEntity> heroes) {
                adapter.updateData(heroes);
            }
        };

        mHeroesViewModel.getItem(getApplicationContext()).observe(this, itemsObserver);
    }
}