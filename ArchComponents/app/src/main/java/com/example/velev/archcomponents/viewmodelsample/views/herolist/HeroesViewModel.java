package com.example.velev.archcomponents.viewmodelsample.views.herolist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.velev.archcomponents.viewmodelsample.data.AppDatabase;
import com.example.velev.archcomponents.viewmodelsample.data.DataRepository;
import com.example.velev.archcomponents.viewmodelsample.data.entity.HeroEntity;

import java.util.List;

/**
 * Created by velev on 15.1.2018 г..
 */

public class HeroesViewModel extends ViewModel {

    private LiveData<List<HeroEntity>> mData = new MutableLiveData<>();
    private AppDatabase mDb;
    private DataRepository dataRepository;

    public LiveData<List<HeroEntity>> getItem(final Context context) {
        mDb = AppDatabase.getDatabase(context.getApplicationContext());
        dataRepository = DataRepository.getInstance(mDb);

        mData = dataRepository.getHeroes();
        return mData;
    }

    public void insertHero(HeroEntity hero) {
        final HeroEntity heroEntity = hero;
        new Thread(new Runnable() {
            @Override
            public void run() {
                dataRepository.insertHero(heroEntity);
            }
        }).start();
    }
}

