package com.example.velev.archcomponents.viewmodelsample.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.example.velev.archcomponents.viewmodelsample.data.entity.HeroEntity;

import java.util.List;

/**
 * Created by velev on 10.2.2018 г..
 */

public class DataRepository {
    private static DataRepository sInstance;

    private final AppDatabase mDatabase;
    private MediatorLiveData<List<HeroEntity>> mObservableProducts;

    private DataRepository(final AppDatabase database) {
        mDatabase = database;
        mObservableProducts = new MediatorLiveData<>();

        mObservableProducts.addSource(mDatabase.heroDao().loadAllHeroes(),
                new Observer<List<HeroEntity>>() {
                    @Override
                    public void onChanged(@Nullable List<HeroEntity> heroEntities) {
                        if (mDatabase.getDatabaseCreated().getValue() != null) {
                            mObservableProducts.postValue(heroEntities);
                        }
                    }
                });
    }

    public static DataRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }


    public LiveData<List<HeroEntity>> getHeroes() {
        return mDatabase.heroDao().loadAllHeroes();
    }

    public void insertHero(HeroEntity hero) {
        mDatabase.heroDao().insertHero(hero);
    }

    public void deleteHero(HeroEntity hero) {
        mDatabase.heroDao().deleteHero(hero);
    }

    public void updateHero(HeroEntity hero) {
        mDatabase.heroDao().updateHero(hero);
    }
}
