package com.example.velev.archcomponents.viewmodelsample.views.herolist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.velev.archcomponents.viewmodelsample.contracts.HeroDataProvider;
import com.example.velev.archcomponents.viewmodelsample.data.HeroesFakeData;
import com.example.velev.archcomponents.viewmodelsample.data.models.Hero;

import java.util.List;

/**
 * Created by velev on 15.1.2018 г..
 */

public class ItemsViewModel extends ViewModel {
    private MutableLiveData<List<Hero>> mData = new MutableLiveData<>();
    private HeroDataProvider mDataProvider;


    public LiveData<List<Hero>> getItem() {
        mDataProvider = new HeroesFakeData();
        final List<Hero> heroes = mDataProvider.getHeroes();

        // simulate network delay
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mData.postValue(heroes);
            }

        }.start();

        return mData;
    }
}

