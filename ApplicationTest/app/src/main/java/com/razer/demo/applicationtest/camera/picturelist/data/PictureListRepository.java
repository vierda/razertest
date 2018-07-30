package com.razer.demo.applicationtest.camera.picturelist.data;


import android.content.Context;

import com.razer.demo.applicationtest.common.entity.Picture;

import java.util.List;

import rx.Observable;


public class PictureListRepository {

    private PictureListDatabaseRepository databaseRepository;

    public PictureListRepository(Context context) {
        databaseRepository = new PictureListDatabaseRepository();
    }

    Observable<List<Picture>> loadPicture() {
        return databaseRepository.getAllPicturesFromDb();
    }
}
