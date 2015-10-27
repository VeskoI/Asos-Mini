package com.veskoiliev.asosmini.ui.singleproduct;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.veskoiliev.asosmini.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Simple fragment that holds a single image. Used in {@link GalleryAdapter}.
 */
public class ImageFragment extends Fragment {

    private static final String KEY_IMAGE_URL = "image_url";

    public static Fragment newInstance(String imageUrl) {
        ImageFragment imageFragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putString(KEY_IMAGE_URL, imageUrl);
        imageFragment.setArguments(args);
        return imageFragment;
    }

    @Bind(R.id.gallery_image)
    ImageView mImageView;

    public ImageFragment() {
        // Empty required constructor.
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gallery_item, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        String imageUrl = getImageUrl();
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.with(getContext()).load(imageUrl).into(mImageView);
        }
    }

    private String getImageUrl() {
        Bundle args = getArguments();
        return args.getString(KEY_IMAGE_URL);
    }
}