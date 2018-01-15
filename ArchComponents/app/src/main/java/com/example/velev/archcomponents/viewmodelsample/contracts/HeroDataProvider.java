package com.example.velev.archcomponents.viewmodelsample.contracts;

import com.example.velev.archcomponents.viewmodelsample.data.models.Hero;

import java.util.List;

/**
 * Created by velev on 14.1.2018 г..
 */

public interface HeroDataProvider {
    List<Hero> getHeroes();
}
