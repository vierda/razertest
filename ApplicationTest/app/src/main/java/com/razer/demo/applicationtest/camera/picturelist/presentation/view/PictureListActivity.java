package com.razer.demo.applicationtest.camera.picturelist.presentation.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.GridView;

import com.razer.demo.applicationtest.R;
import com.razer.demo.applicationtest.common.entity.Picture;
import com.razer.demo.applicationtest.common.util.Constants;
import com.razer.demo.applicationtest.camera.picturedetail.presentation.view.DetailPictureActivity;
import com.razer.demo.applicationtest.camera.picturelist.presentation.adapter.PictureAdapter;
import com.razer.demo.applicationtest.camera.picturelist.presentation.viewmodel.PictureListViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class PictureListActivity extends AppCompatActivity {

    @BindView(R.id.gridview)
    GridView gridView;

    private PictureListViewModel pictureListViewModel;
    private LiveData<List<Picture>> pictureListLiveData;
    private PictureAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_list_activity);

        ButterKnife.bind(this);

        DisplayMetrics displayMetrics = this.getResources()
                .getDisplayMetrics();
        float screenWidth = displayMetrics.widthPixels;
        int columnWidth = (int) ((screenWidth / 2));
        gridView.setColumnWidth(columnWidth);
        gridView.setVerticalSpacing(2);
        gridView.setHorizontalSpacing(2);

        pictureListViewModel = ViewModelProviders.of(PictureListActivity.this).get(PictureListViewModel.class);
        pictureListViewModel.getPictureFromDb();

        pictureListLiveData = pictureListViewModel.getPictureListLiveData();
        pictureListLiveData.observe(PictureListActivity.this, new Observer<List<Picture>>() {

            @Override
            public void onChanged(@Nullable List<Picture> products) {
                adapter = new PictureAdapter(PictureListActivity.this, products);
                gridView.setAdapter(adapter);
            }
        });

    }

    @OnItemClick(R.id.gridview)
    void onItemClick(int position) {
        Intent myIntent = new Intent(this, DetailPictureActivity.class);
        myIntent.putExtra(Constants.PICTURE_ID, adapter.getItem(position).getId());
        startActivity(myIntent);
    }

}
