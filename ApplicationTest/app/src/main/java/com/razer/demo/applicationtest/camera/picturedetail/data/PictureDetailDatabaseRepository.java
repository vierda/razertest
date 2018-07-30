package com.razer.demo.applicationtest.camera.picturedetail.data;

import com.razer.demo.applicationtest.TestApplication;
import com.razer.demo.applicationtest.common.entity.Picture;

import rx.Observable;
import rx.Subscriber;

public class PictureDetailDatabaseRepository {

    Observable<Picture> getPictureDetail(final long pictureId) {
        return Observable.unsafeCreate(new Observable.OnSubscribe<Picture>() {
            @Override
            public void call(final Subscriber<? super Picture> subscriber) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Picture product = TestApplication.getInstance().getDatabase()
                                .pictureDao().findByPictureId(pictureId);
                        subscriber.onNext(product);
                        subscriber.onCompleted();
                    }
                }).start();

            }
        });

    }

    Observable<Boolean> savePictureDescription(final String newDescription, final long pictureId) {
        return Observable.unsafeCreate(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(final Subscriber<? super Boolean> subscriber) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        TestApplication.getInstance().getDatabase()
                                .pictureDao().updatePictureDescription(newDescription, pictureId);
                        subscriber.onNext(true);
                        subscriber.onCompleted();
                    }
                }).start();

            }
        });
    }
}
