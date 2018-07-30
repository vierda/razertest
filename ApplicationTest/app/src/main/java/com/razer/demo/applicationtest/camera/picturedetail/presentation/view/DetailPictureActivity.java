package com.razer.demo.applicationtest.camera.picturedetail.presentation.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.razer.demo.applicationtest.R;
import com.razer.demo.applicationtest.common.entity.Picture;
import com.razer.demo.applicationtest.common.util.Constants;
import com.razer.demo.applicationtest.camera.picturedetail.presentation.viewmodel.DetailPictureViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailPictureActivity extends AppCompatActivity {

    @BindView(R.id.picture)
    ImageView pictureImageView;

    @BindView(R.id.picture_description)
    TextView pictureDescription;

    @BindView(R.id.edit_picture_description)
    EditText editPictureDescription;

//    @BindView(R.id.edit_container)
//    LinearLayout editContainer;

    private long pictureId = 0L;
    private DetailPictureViewModel viewModel;
    private LiveData<Picture> picturetLiveData;
    private LiveData<Boolean> saveLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.picture_detail_activity);

        ButterKnife.bind(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        pictureId = getIntent().getLongExtra(Constants.PICTURE_ID, 0L);
        viewModel = ViewModelProviders.of(DetailPictureActivity.this).get(DetailPictureViewModel.class);

        viewModel.getPictureDetail(pictureId);
        observeDetail();
    }


    private void observeDetail() {
        picturetLiveData = viewModel.getPictureLiveData();
        picturetLiveData.observe(DetailPictureActivity.this, new Observer<Picture>() {

            @Override
            public void onChanged(@Nullable Picture picture) {
                if (picture != null) {

                    pictureDescription.setText(picture.getDescription());

                    Glide.with(DetailPictureActivity.this)
                            .load(picture.getImage())
                            .asBitmap()
                            .placeholder(R.drawable.empty)
                            .into(pictureImageView);

                }

            }
        });
    }

//    @OnClick(R.id.add_edit_description)
//    public void editDescription() {
//        editContainer.setVisibility(View.VISIBLE);
//    }


    @OnClick(R.id.save_description)
    public void saveDescription() {
        viewModel.savePictureDescription(editPictureDescription.getText().toString(), pictureId);
        observeSaveDescription();

    }

    private void observeSaveDescription() {
        saveLiveData = viewModel.getSaveDescriptionLiveData();
        saveLiveData.observe(DetailPictureActivity.this, new Observer<Boolean>() {

            @Override
            public void onChanged(@Nullable Boolean saved) {
                if (saved) {
                    pictureDescription.setText(editPictureDescription.getText());
                    editPictureDescription.setText("");
                    editPictureDescription.setHint(R.string.edit_description);
                   // editContainer.setVisibility(View.GONE);
                }

            }
        });

    }

}
