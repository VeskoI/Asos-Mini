package com.veskoiliev.asosmini;

import android.os.Bundle;

import com.veskoiliev.asosmini.model.DataWrapper;
import com.veskoiliev.asosmini.model.DataWrapperImpl;
import com.veskoiliev.asosmini.model.pojo.Category;
import com.veskoiliev.asosmini.model.pojo.Product;
import com.veskoiliev.asosmini.ui.DataFetchedListener;

import java.util.List;

import static com.veskoiliev.asosmini.model.db.Contract.Category.GENDER_MEN;
import static com.veskoiliev.asosmini.model.db.Contract.Category.GENDER_WOMEN;

public class MainActivityPresenterImpl implements MainActivityPresenter, DataFetchedListener {

    private static final String KEY_SELECTED_GENDER = "SELECTED_GENDER";
    private static final String KEY_SELECTED_CATEGORY = "SELECTED_CATEGORY";
    private static final String TAG = "vesko";

    private MainView mView;
    private DataWrapper mDataWrapper;

    private int mSelectedGender;
    private long mSelectedCategory;

    public MainActivityPresenterImpl(MainView mainView) {
        mView = mainView;
        mDataWrapper = new DataWrapperImpl();
        mSelectedGender = GENDER_MEN;
    }

    @Override
    public void onCreate(boolean men) {
        loadCategories(men);

        if (mSelectedCategory == 0) {
            mView.openDrawer();
        }
    }

    @Override
    public void onRecreate(boolean men) {
        loadCategories(men);
    }

    private void loadCategories(boolean men) {
        mSelectedGender = men ? GENDER_MEN : GENDER_WOMEN;
        mDataWrapper.loadCategories(men, this);
    }

    @Override
    public void onMenuWomenClicked() {
        if (mSelectedGender == GENDER_WOMEN) {
            // We're already displaying those, nothing to do here.
            return;
        }

        onCreate(true);
    }

    @Override
    public void onMenuMenClicked() {
        if (mSelectedGender == GENDER_MEN) {
            // We're already displaying those, nothing to do here.
            return;
        }

        onCreate(false);
    }

    @Override
    public void onCategorySelected(long id) {
        mSelectedCategory = id;

        mView.onMenuItemSelected(id);
        mDataWrapper.loadProductsForCategory(id, this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_SELECTED_GENDER, mSelectedGender);
        outState.putLong(KEY_SELECTED_CATEGORY, mSelectedCategory);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        mSelectedGender = savedInstanceState.getInt(KEY_SELECTED_GENDER);
        mSelectedCategory = savedInstanceState.getLong(KEY_SELECTED_CATEGORY);

        onCategorySelected(mSelectedCategory);
    }

    @Override
    public void onCategoriesLoaded(List<Category> categories) {
        mView.onCategoriesLoaded(categories);
    }

    @Override
    public void onDataError(String errorMessage) {
        // TODO: 10/26/2015
    }

    @Override
    public void onProductsLoaded(List<Product> products) {
        mView.onProductsLoaded(products);
    }
}
