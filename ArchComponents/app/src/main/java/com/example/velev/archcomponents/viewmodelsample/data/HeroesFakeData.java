package com.example.velev.archcomponents.viewmodelsample.data;

import android.arch.lifecycle.LiveData;

import com.example.velev.archcomponents.viewmodelsample.data.dao.HeroDao;
import com.example.velev.archcomponents.viewmodelsample.data.entity.HeroEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 15.1.2018 г..
 */

public class HeroesFakeData implements HeroDao {
    private List<HeroEntity> mHeroes;

    public HeroesFakeData() {
        mHeroes = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            mHeroes.addAll(loadHeroes());
        }
    }


    public List<HeroEntity> getHeroes() {
        return mHeroes;
    }

    private List<HeroEntity> loadHeroes() {
        List<HeroEntity> heroes = new ArrayList<>();

        heroes.add(new HeroEntity("batman", "http..."));
        heroes.add(new HeroEntity("superman", "http..."));
        heroes.add(new HeroEntity("catwoman", "http..."));
        heroes.add(new HeroEntity("spiderman", "http..."));
        heroes.add(new HeroEntity("punisher", "http..."));

        return heroes;
    }

    @Override
    public LiveData<List<HeroEntity>> loadAllHeroes() {
        return null;
    }

    @Override
    public void insertHero(HeroEntity hero) {

    }

    @Override
    public void updateHero(HeroEntity hero) {

    }

    @Override
    public void deleteHero(HeroEntity hero) {

    }
}

