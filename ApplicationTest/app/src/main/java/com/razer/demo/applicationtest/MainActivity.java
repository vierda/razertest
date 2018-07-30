package com.razer.demo.applicationtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.razer.demo.applicationtest.camera.picturelist.presentation.view.PictureListActivity;
import com.razer.demo.applicationtest.camera.takepicture.presentation.view.TakePictureActivity;
import com.razer.demo.applicationtest.visualizer.presentation.view.VisualizerActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);


    }

    @OnClick(R.id.take_picture)
    public void onTakePictureClick() {
        Intent intent = new Intent(this, TakePictureActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.picture_list)
    public void onPictureList() {
        Intent intent = new Intent(this, PictureListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.visualizer)
    public void onVisualizerClick() {
        Intent intent = new Intent(this, VisualizerActivity.class);
        startActivity(intent);
    }
}
