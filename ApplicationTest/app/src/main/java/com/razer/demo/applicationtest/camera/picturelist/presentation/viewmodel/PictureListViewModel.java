package com.razer.demo.applicationtest.camera.picturelist.presentation.viewmodel;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.razer.demo.applicationtest.common.entity.Picture;
import com.razer.demo.applicationtest.common.util.DefaultSubscriber;
import com.razer.demo.applicationtest.camera.picturelist.data.PictureListUseCase;

import java.util.List;

public class PictureListViewModel extends AndroidViewModel {

    PictureListUseCase useCase;
    MutableLiveData<List<Picture>> liveData;

    public PictureListViewModel(@NonNull Application application) {
        super(application);
        useCase = new PictureListUseCase(application);
    }

    public MutableLiveData<List<Picture>> getPictureListLiveData() {
        if (liveData == null) {
            liveData = new MutableLiveData<>();
        }
        return liveData;
    }

    public void getPictureFromDb() {
        useCase.loadPictures(new DefaultSubscriber<List<Picture>>() {
            @Override
            public void onCompleted() {
                //no-opt
            }

            @Override
            public void onError(Throwable e) {
                //TODO
            }

            @Override
            public void onNext(List<Picture> pictures) {

                liveData.postValue(pictures);
            }
        });
    }
}
