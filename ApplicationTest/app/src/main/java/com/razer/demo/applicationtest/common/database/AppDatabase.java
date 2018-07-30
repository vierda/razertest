package com.razer.demo.applicationtest.common.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.razer.demo.applicationtest.common.dao.PictureDao;
import com.razer.demo.applicationtest.common.entity.Picture;

@Database(entities = {Picture.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PictureDao pictureDao();
}
