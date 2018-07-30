package com.razer.demo.applicationtest.camera.picturelist.presentation.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.razer.demo.applicationtest.R;
import com.razer.demo.applicationtest.common.entity.Picture;

import java.util.List;

public class PictureAdapter extends BaseAdapter {

    private List<Picture> mItems;
    private final LayoutInflater mInflater;
    private Context context;

    public PictureAdapter(Context context, List<Picture> items) {
        mInflater = LayoutInflater.from(context);
        mItems = items;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ImageView picture;
        TextView name;

        if (view == null) {
            view = mInflater.inflate(R.layout.picture_item, parent, false);
            view.setTag(R.id.picture, view.findViewById(R.id.picture));
            view.setTag(R.id.text, view.findViewById(R.id.text));
        }


        Picture item = getItem(position);

        picture = (ImageView) view.getTag(R.id.picture);
        Glide.with(context)
                .load(item.getImage())
                .asBitmap()
                .placeholder(R.drawable.empty)
                .into(picture);
        return view;
    }


    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }

    @Override
    public int getCount() {
        if (mItems != null)
            return mItems.size();
        else
            return 0;
    }

    @Override
    public Picture getItem(int i) {
        if (mItems != null)
            return mItems.get(i);
        else
            return null;
    }

    @Override
    public long getItemId(int i) {
        if (mItems != null)
            return mItems.get(i).getId();
        else
            return 0;

    }

}
