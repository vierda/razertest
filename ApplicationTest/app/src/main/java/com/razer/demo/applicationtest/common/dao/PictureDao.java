package com.razer.demo.applicationtest.common.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.razer.demo.applicationtest.common.entity.Picture;

import java.util.List;

@Dao
public interface PictureDao {

    @Query("SELECT * FROM picture")
    List<Picture> getAll();

    @Query("SELECT * FROM picture where id LIKE  :pictureId")
    Picture findByPictureId(long pictureId);

    @Query("SELECT * FROM picture LIMIT  :start,:end")
    List<Picture> getPictureByLimit(int start, int end);

    @Query("UPDATE picture SET description=:description WHERE id = :pictureId")
    void updatePictureDescription(String description, long pictureId);

    @Query("SELECT COUNT(*) from picture")
    int count();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPicture(Picture... pictures);

    @Delete
    void delete(Picture picture);

    @Query("DELETE FROM Picture")
    void deleteAllPicture();
}
