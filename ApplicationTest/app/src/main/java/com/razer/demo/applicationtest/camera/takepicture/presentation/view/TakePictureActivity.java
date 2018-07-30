package com.razer.demo.applicationtest.camera.takepicture.presentation.view;

import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.razer.demo.applicationtest.R;
import com.razer.demo.applicationtest.camera.takepicture.presentation.viewmodel.PictureTakenViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TakePictureActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;


    @BindView(R.id.picture_taken)
    ImageView pictureTaken;

    @BindView(R.id.confirmation_container)
    LinearLayout confirmationContainer;

    private Bitmap photo;
    private PictureTakenViewModel pictureTakenViewModel;
    private LiveData<Boolean> savePictureLiveData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_capture_activity);

        ButterKnife.bind(this);

        pictureTakenViewModel = ViewModelProviders.of(TakePictureActivity.this).get(PictureTakenViewModel.class);
        savePictureLiveData = pictureTakenViewModel.getSavePictureLiveData();
    }

    @OnClick(R.id.capture)
    public void onTakePictureClick() {
        if (checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    MY_CAMERA_PERMISSION_CODE);
        } else {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            confirmationContainer.setVisibility(View.VISIBLE);

            photo = (Bitmap) data.getExtras().get("data");
            pictureTaken.setImageBitmap(photo);
        }
    }


    @OnClick(R.id.yes)
    public void onYesClick() {
        pictureTakenViewModel.savePicture(photo);
        savePictureLiveData.observe(TakePictureActivity.this, new Observer<Boolean>() {

            @Override
            public void onChanged(@Nullable Boolean saved) {
                Toast.makeText(TakePictureActivity.this, getString(R.string.picture_saved),
                        Toast.LENGTH_SHORT).show();
                confirmationContainer.setVisibility(View.GONE);
            }
        });
    }

    @OnClick(R.id.no)
    public void onNoClick() {
        confirmationContainer.setVisibility(View.GONE);
    }
}
