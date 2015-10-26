package com.veskoiliev.asosmini.ui.singleproduct;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GalleryAdapter extends FragmentStatePagerAdapter {

    private static final int INITIAL_CAPACITY = 10;

    private List<String> imageUrls = new ArrayList<>(INITIAL_CAPACITY);

    public GalleryAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public void setImages(List<String> images) {
        imageUrls.clear();
        imageUrls.addAll(images);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        if (position < 0 || position > imageUrls.size()) {
            throw new IllegalStateException("Trying to access invalid item at position: " + position);
        }

        return ImageFragment.newInstance(imageUrls.get(position));
    }

    @Override
    public int getCount() {
        return imageUrls != null ? imageUrls.size() : 0;
    }
}
