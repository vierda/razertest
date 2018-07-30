package com.razer.demo.applicationtest.camera.takepicture.data;

import android.graphics.Bitmap;

import com.razer.demo.applicationtest.TestApplication;
import com.razer.demo.applicationtest.common.entity.Picture;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import rx.Observable;
import rx.Subscriber;

public class PictureTakenDatabaseRepository {

    Observable<Boolean> savePicture(final Bitmap photo) {
        return Observable.unsafeCreate(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(final Subscriber<? super Boolean> subscriber) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        byte[] byteArray = null;

                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        if (photo != null) {
                            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byteArray = stream.toByteArray();
                        }


                        Picture picture = new Picture();
                        picture.setId(new Date().getTime());
                        picture.setImage(byteArray);

                        TestApplication.getInstance().getDatabase()
                                .pictureDao().insertPicture(picture);
                        subscriber.onNext(true);
                        subscriber.onCompleted();
                    }
                }).start();

            }
        });
    }
}
