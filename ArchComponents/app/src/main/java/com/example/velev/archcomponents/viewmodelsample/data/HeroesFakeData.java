package com.example.velev.archcomponents.viewmodelsample.data;

import com.example.velev.archcomponents.viewmodelsample.contracts.HeroDataProvider;
import com.example.velev.archcomponents.viewmodelsample.data.models.Hero;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 15.1.2018 г..
 */

public class HeroesFakeData implements HeroDataProvider {
    private List<Hero> mHeroes;

    public HeroesFakeData() {
        mHeroes = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            mHeroes.addAll(loadHeroes());
        }
    }

    @Override
    public List<Hero> getHeroes() {
        return mHeroes;
    }

    private List<Hero> loadHeroes() {
        List<Hero> heroes = new ArrayList<>();

        heroes.add(new Hero("batman", "http..."));
        heroes.add(new Hero("superman", "http..."));
        heroes.add(new Hero("catwoman", "http..."));
        heroes.add(new Hero("spiderman", "http..."));
        heroes.add(new Hero("punisher", "http..."));

        return heroes;
    }
}

