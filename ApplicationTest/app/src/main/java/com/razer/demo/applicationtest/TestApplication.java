package com.razer.demo.applicationtest;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.razer.demo.applicationtest.common.database.AppDatabase;

public class TestApplication extends Application {

    public static  TestApplication _instance;
    private static final String DATABASE_NAME = "RazerDb";
    private AppDatabase database;

    public static TestApplication getInstance() { return _instance;}

    @Override
    public void onCreate() {
        super.onCreate();

        _instance = this;

        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                .build();
    }

    public AppDatabase getDatabase () {
        return database;
    }
}
