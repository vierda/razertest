package com.razer.demo.applicationtest.camera.picturedetail.data;

import android.content.Context;

import com.razer.demo.applicationtest.common.entity.Picture;

import rx.Observable;

public class PictureDetailRepository {

    private PictureDetailDatabaseRepository databaseRepository;

    public PictureDetailRepository(Context context) {
        databaseRepository = new PictureDetailDatabaseRepository();
    }

    Observable<Picture> getPictureDetail(long pictureId) {
        return databaseRepository.getPictureDetail(pictureId);
    }


    Observable<Boolean> savePictureDescription(String newDescription, long pictureId) {
        return databaseRepository.savePictureDescription(newDescription, pictureId);
    }

}
