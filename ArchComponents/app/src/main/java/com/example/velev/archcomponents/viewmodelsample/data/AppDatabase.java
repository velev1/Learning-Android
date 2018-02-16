package com.example.velev.archcomponents.viewmodelsample.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import com.example.velev.archcomponents.viewmodelsample.data.dao.HeroDao;
import com.example.velev.archcomponents.viewmodelsample.data.entity.HeroEntity;

/**
 * Created by velev on 10.2.2018 г..
 */

@Database(entities = {HeroEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    @VisibleForTesting
    public static final String DATABASE_NAME = "hero-db";
    private static AppDatabase INSTANCE;
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public abstract HeroDao heroDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                 //   Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                            // To simplify the codelab, allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
//                             .allowMainThreadQueries()
                            .build();
            INSTANCE.setDatabaseCreated();
        }
        return INSTANCE;
    }

//    private void updateDatabaseCreated(final Context context) {
//        if (context.getDatabasePath(DATABASE_NAME).exists()) {
//            setDatabaseCreated();
//        }
//    }

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }
}
