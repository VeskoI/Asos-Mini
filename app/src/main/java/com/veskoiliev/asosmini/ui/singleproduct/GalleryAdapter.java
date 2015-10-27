package com.veskoiliev.asosmini.ui.singleproduct;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple adapter used to display our main image gallery in {@link SingleProductActivity}.
 * Uses {@link ImageFragment} for each "page".
 */
public class GalleryAdapter extends FragmentStatePagerAdapter {

    private static final int INITIAL_CAPACITY = 10;

    private List<String> imageUrls = new ArrayList<>(INITIAL_CAPACITY);

    public GalleryAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    /**
     * Use this method to provide images to the adapter.
     * Internally it calls {@link #notifyDataSetChanged()} so no external refreshing is needed.
     *
     * @param images list of images to be displayed.
     */
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
