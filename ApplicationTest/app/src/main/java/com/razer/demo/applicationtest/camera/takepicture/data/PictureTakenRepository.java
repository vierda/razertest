package com.razer.demo.applicationtest.camera.takepicture.data;

import android.content.Context;
import android.graphics.Bitmap;

import rx.Observable;

public class PictureTakenRepository {

    private PictureTakenDatabaseRepository databaseRepository;

    public PictureTakenRepository(Context context) {
        databaseRepository = new PictureTakenDatabaseRepository();
    }

    Observable<Boolean> savePicture(Bitmap photo) {
        return databaseRepository.savePicture(photo);
    }

}
