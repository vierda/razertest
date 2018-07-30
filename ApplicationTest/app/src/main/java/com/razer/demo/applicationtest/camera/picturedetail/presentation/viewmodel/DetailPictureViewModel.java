package com.razer.demo.applicationtest.camera.picturedetail.presentation.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.razer.demo.applicationtest.common.entity.Picture;
import com.razer.demo.applicationtest.common.util.DefaultSubscriber;
import com.razer.demo.applicationtest.camera.picturedetail.data.PictureDetailUseCase;

public class DetailPictureViewModel extends AndroidViewModel {

    PictureDetailUseCase useCase;
    MutableLiveData<Picture> pictureLiveData;
    MutableLiveData<Boolean> saveDescriptionLiveData;

    public DetailPictureViewModel(@NonNull Application application) {
        super(application);
        useCase = new PictureDetailUseCase(application);
    }

    public MutableLiveData<Picture> getPictureLiveData() {
        if (pictureLiveData == null) {
            pictureLiveData = new MutableLiveData<>();
        }
        return pictureLiveData;
    }

    public MutableLiveData<Boolean> getSaveDescriptionLiveData() {
        if (saveDescriptionLiveData == null) {
            saveDescriptionLiveData = new MutableLiveData<>();
        }
        return saveDescriptionLiveData;
    }

    public void getPictureDetail(long pictureId) {
        useCase.getPictureDetail(new DefaultSubscriber<Picture>() {
            @Override
            public void onCompleted() {
                //no-opt
            }

            @Override
            public void onError(Throwable e) {
                //TODO
            }

            @Override
            public void onNext(Picture picture) {
                pictureLiveData.postValue(picture);
            }
        }, pictureId);
    }


    public void savePictureDescription(String newDescription, long pictureId) {
        useCase.savePictureDescription(new DefaultSubscriber<Boolean>() {
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
                saveDescriptionLiveData.postValue(saved);
            }
        }, newDescription, pictureId);
    }
}
