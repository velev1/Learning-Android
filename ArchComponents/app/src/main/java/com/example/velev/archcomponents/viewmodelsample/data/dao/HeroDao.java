package com.example.velev.archcomponents.viewmodelsample.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.velev.archcomponents.viewmodelsample.data.entity.HeroEntity;

import java.util.List;

/**
 * Created by velev on 10.2.2018 г..
 */

@Dao
public interface HeroDao {
    @Query("SELECT * FROM heroes")
    LiveData<List<HeroEntity>> loadAllHeroes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHero(HeroEntity hero);

    @Update
    void updateHero(HeroEntity hero);

    @Delete
    void deleteHero(HeroEntity hero);
}
