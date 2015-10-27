package com.veskoiliev.asosmini.ui.singleproduct;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.veskoiliev.asosmini.R;
import com.veskoiliev.asosmini.model.pojo.Product;
import com.veskoiliev.asosmini.model.pojo.ProductDetails;
import com.veskoiliev.asosmini.ui.BaseActivity;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingleProductActivity extends BaseActivity implements SingleProductView {

    private static final String EXTRA_PRODUCT_ID = "PRODUCT_ID";

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
        if (productId <= 0) {
            throw new IllegalArgumentException("No ProductId passed to SingleProductActivity!");
        }

        mGalleryAdapter = new GalleryAdapter(getSupportFragmentManager());
        mGalleryPager.setAdapter(mGalleryAdapter);

        mPresenter = new SingleProductPresenterImpl(this);
        mPresenter.onOpened(productId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_favorites) {
            mPresenter.onFavoritesClicked();
            return true;
        } else if (id == R.id.action_bag) {
            mPresenter.onBagClicked();
            return true;
        } else if (id == android.R.id.home) {
            // No need to recreate the Parent activity,
            // we can just finish this one and return to the parent.
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.product_details_add_to_bag)
    public void onAddToBagClicked() {
        mPresenter.onAddToBagClicked();
    }

    @Override
    public void onProductLoaded(ProductDetails productDetails) {
        mBrand.setText(productDetails.getBrand());
        mDescription.setText(productDetails.getDescription());
        mAddToBag.setText(getString(R.string.add_to_bag_with_price, productDetails.getCurrentPrice()));
        mGalleryAdapter.setImages(productDetails.getImages());
    }

    @Override
    public void onError(String errorMessage) {
        Snackbar.make(mToolbar, errorMessage, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onDisplayFavorites(HashMap<Long, Product> favorites) {
        displayFavorites(favorites);
    }

    @Override
    public void onDisplayBag(HashMap<Long, Integer> bag) {
        displayBag(bag);
    }

    @Override
    public void confirmProductAddedToBag() {
        Snackbar.make(mToolbar, getString(R.string.added_to_bag), Snackbar.LENGTH_LONG).show();
    }
}
