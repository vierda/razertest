package com.razer.demo.applicationtest.visualizer.presentation.view;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import com.razer.demo.applicationtest.R;
import com.razer.demo.applicationtest.common.widget.VerticalSeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VisualizerActivity extends AppCompatActivity {

    @BindView(R.id.seek_bar_1)
    VerticalSeekBar seekBar1;

    @BindView(R.id.seek_bar_2)
    VerticalSeekBar seekBar2;

    @BindView(R.id.seek_bar_3)
    VerticalSeekBar seekBar3;

    @BindView(R.id.seek_bar_4)
    VerticalSeekBar seekBar4;

    @BindView(R.id.seek_bar_5)
    VerticalSeekBar seekBar5;

    @BindView(R.id.seek_bar_6)
    VerticalSeekBar seekBar6;

    @BindView(R.id.seek_bar_7)
    VerticalSeekBar seekBar7;

    @BindView(R.id.seek_bar_8)
    VerticalSeekBar seekBar8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizer_activity);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.game_button)
    public void gameButtonClick() {
        setRandomValue();
    }

    @OnClick(R.id.movie_button)
    public void movieButtonClick() {
        setRandomValue();
    }

    @OnClick(R.id.music_button)
    public void musicButtonClick() {
        setRandomValue();
    }

    private void setRandomValue() {
        animate(seekBar1, (int) (Math.random() * 100));
        animate(seekBar2, (int) (Math.random() * 100));
        animate(seekBar3, (int) (Math.random() * 100));
        animate(seekBar4, (int) (Math.random() * 100));
        animate(seekBar5, (int) (Math.random() * 100));
        animate(seekBar6, (int) (Math.random() * 100));
        animate(seekBar7, (int) (Math.random() * 100));
        animate(seekBar8, (int) (Math.random() * 100));
    }

    private void animate(VerticalSeekBar verticalSeekBar, int fromValue) {
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(verticalSeekBar,
                "progress", fromValue);
        progressAnimator.setDuration(700);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();
        progressAnimator.setRepeatCount(Animation.INFINITE);
    }
}
