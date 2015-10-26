package com.veskoiliev.asosmini.ui.singleproduct;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.veskoiliev.asosmini.R;
import com.veskoiliev.asosmini.model.pojo.ProductDetails;
import com.veskoiliev.asosmini.ui.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SingleProductActivity extends BaseActivity implements SingleProductView {

    private static final String EXTRA_PRODUCT_ID = "PRODUCT_ID";
    private static final String TAG = "vesko";

    public static Intent getStartIntent(Context context, long productId) {
        Intent intent = new Intent(context, SingleProductActivity.class);
        intent.putExtra(EXTRA_PRODUCT_ID, productId);
        return intent;
    }

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.product_details_gallery_pager)
    ViewPager mGalleryPager;

    @Bind(R.id.product_details_brand)
    TextView mBrand;

    @Bind(R.id.product_details_description)
    TextView mDescription;

    @Bind(R.id.product_details_add_to_bag)
    Button mAddToBag;

    private SingleProductPresenter mPresenter;
    private GalleryAdapter mGalleryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        long productId = getIntent().getExtras().getLong(EXTRA_PRODUCT_ID);

        mGalleryAdapter = new GalleryAdapter(getSupportFragmentManager());
        mGalleryPager.setAdapter(mGalleryAdapter);

        mPresenter = new SingleProductPresenterImpl(this);
        mPresenter.onOpened(productId);
    }

    @Override
    public void onProductLoaded(ProductDetails productDetails) {
        Log.d(TAG, "SingleProductActivity, onProductLoaded: " + productDetails);
        mBrand.setText(productDetails.getBrand());
        mDescription.setText(productDetails.getDescription());
        mAddToBag.setText(getString(R.string.add_to_bag_with_price, productDetails.getCurrentPrice()));
        mGalleryAdapter.setImages(productDetails.getImages());
    }

    @Override
    public void onError(String errorMessage) {
        Snackbar.make(mToolbar, errorMessage, Snackbar.LENGTH_LONG).show();
    }
}
