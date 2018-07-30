package com.razer.demo.applicationtest.camera.takepicture.data;

import android.content.Context;
import android.graphics.Bitmap;

import com.razer.demo.applicationtest.common.util.DefaultSubscriber;
import com.razer.demo.applicationtest.common.util.UseCase;

public class PictureTakenUseCase extends UseCase {

    private PictureTakenRepository repository;

    public PictureTakenUseCase(Context context) {
        repository = new PictureTakenRepository(context);
    }

    public void savePicture(DefaultSubscriber<Boolean> subscriber, Bitmap photo) {
        execute(repository.savePicture(photo), subscriber);
    }
}
