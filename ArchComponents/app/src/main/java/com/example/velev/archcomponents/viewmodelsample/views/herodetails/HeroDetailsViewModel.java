package com.example.velev.archcomponents.viewmodelsample.views.herodetails;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.velev.archcomponents.viewmodelsample.data.AppDatabase;
import com.example.velev.archcomponents.viewmodelsample.data.DataRepository;
import com.example.velev.archcomponents.viewmodelsample.data.entity.HeroEntity;

/**
 * Created by velev on 16.2.2018 г..
 */

public class HeroDetailsViewModel extends ViewModel {

    private DataRepository mDataRepository;

    public void deleteHero(HeroEntity heroEntity, Context context) {
        final HeroEntity hero = heroEntity;
        AppDatabase db = AppDatabase.getDatabase(context.getApplicationContext());
        mDataRepository = DataRepository.getInstance(db);

        new Thread(new Runnable() {
            @Override
            public void run() {
                mDataRepository.deleteHero(hero);
            }
        }).start();
    }

    public void updateHero(HeroEntity heroEntity, Context context) {
        final HeroEntity hero = heroEntity;
        AppDatabase db = AppDatabase.getDatabase(context.getApplicationContext());
        mDataRepository = DataRepository.getInstance(db);

        new Thread(new Runnable() {
            @Override
            public void run() {
                mDataRepository.updateHero(hero);
            }
        }).start();
    }
}
