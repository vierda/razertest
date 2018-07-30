package com.razer.demo.applicationtest.camera.takepicture.presentation.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.razer.demo.applicationtest.common.util.DefaultSubscriber;
import com.razer.demo.applicationtest.camera.takepicture.data.PictureTakenUseCase;

public class PictureTakenViewModel extends AndroidViewModel {

    PictureTakenUseCase useCase;
    MutableLiveData<Boolean> savePictureLiveData;

    public PictureTakenViewModel(@NonNull Application application) {
        super(application);
        useCase = new PictureTakenUseCase(application);
    }

    public MutableLiveData<Boolean> getSavePictureLiveData() {
        if (savePictureLiveData == null) {
            savePictureLiveData = new MutableLiveData<>();
        }
        return savePictureLiveData;
    }


    public void savePicture(Bitmap photo) {
        useCase.savePicture(new DefaultSubscriber<Boolean>() {
            @Override
            public void onCompleted() {
                //no-opt
            }

            @Override
            public void onError(Throwable e) {
                //TODO
            }

            @Override
            public void onNext(Boolean saved) {
                savePictureLiveData.postValue(saved);
            }
        }, photo);
    }
}
