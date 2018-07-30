package com.razer.demo.applicationtest.camera.picturedetail.data;

import android.content.Context;

import com.razer.demo.applicationtest.common.entity.Picture;
import com.razer.demo.applicationtest.common.util.DefaultSubscriber;
import com.razer.demo.applicationtest.common.util.UseCase;

public class PictureDetailUseCase extends UseCase {

    private PictureDetailRepository repository;

    public PictureDetailUseCase(Context context) {
        repository = new PictureDetailRepository(context);
    }

    public void getPictureDetail(DefaultSubscriber<Picture> subscriber, long pictureId) {
        execute(repository.getPictureDetail(pictureId), subscriber);
    }

    public void savePictureDescription(DefaultSubscriber<Boolean> subscriber, String newDescription, long pictureId) {
        execute(repository.savePictureDescription(newDescription, pictureId), subscriber);
    }
}
