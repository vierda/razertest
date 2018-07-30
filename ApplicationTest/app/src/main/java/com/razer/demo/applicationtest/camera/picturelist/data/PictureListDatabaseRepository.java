package com.razer.demo.applicationtest.camera.picturelist.data;

import com.razer.demo.applicationtest.TestApplication;
import com.razer.demo.applicationtest.common.entity.Picture;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class PictureListDatabaseRepository {

    Observable<List<Picture>> getAllPicturesFromDb() {
        return Observable.unsafeCreate(new Observable.OnSubscribe<List<Picture>>() {
            @Override
            public void call(final Subscriber<? super List<Picture>> subscriber) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        List<Picture> pictures = TestApplication.getInstance().getDatabase()
                                .pictureDao().getAll();
                        subscriber.onNext(pictures);
                        subscriber.onCompleted();

                    }
                }).start();
            }
        });
    }
}
