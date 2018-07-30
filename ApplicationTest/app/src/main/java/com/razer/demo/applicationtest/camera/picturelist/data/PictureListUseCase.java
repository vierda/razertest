package com.razer.demo.applicationtest.camera.picturelist.data;


import android.content.Context;

import com.razer.demo.applicationtest.common.entity.Picture;
import com.razer.demo.applicationtest.common.util.DefaultSubscriber;
import com.razer.demo.applicationtest.common.util.UseCase;

import java.util.List;

public class PictureListUseCase extends UseCase {

    private PictureListRepository repository;

    public PictureListUseCase(Context context) {
        repository = new PictureListRepository(context);
    }

    public void loadPictures(DefaultSubscriber<List<Picture>> subscriber) {
        execute(repository.loadPicture(), subscriber);
    }
}
