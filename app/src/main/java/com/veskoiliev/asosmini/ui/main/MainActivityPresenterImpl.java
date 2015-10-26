package com.veskoiliev.asosmini.ui.main;

import android.os.Bundle;
import android.util.Log;

import com.veskoiliev.asosmini.model.Gender;
import com.veskoiliev.asosmini.model.DataWrapper;
import com.veskoiliev.asosmini.model.DataWrapperImpl;
import com.veskoiliev.asosmini.model.pojo.Category;
import com.veskoiliev.asosmini.model.pojo.Product;
import com.veskoiliev.asosmini.model.DataFetchedListener;

import java.util.HashMap;
import java.util.List;

import static com.veskoiliev.asosmini.model.Gender.MEN;
import static com.veskoiliev.asosmini.model.Gender.WOMEN;

public class MainActivityPresenterImpl implements MainActivityPresenter, DataFetchedListener {

    private static final String KEY_SELECTED_GENDER = "SELECTED_GENDER";
    private static final String KEY_SELECTED_CATEGORY = "SELECTED_CATEGORY";
    private static final long NOT_SET = -1;

    private static final String TAG = "vesko";

    private MainView mView;
    private DataWrapper mDataWrapper;

    private long mSelectedCategory = NOT_SET;
    private Gender mSelectedGender;

    public MainActivityPresenterImpl(MainView mainView) {
        mView = mainView;
        mDataWrapper = new DataWrapperImpl();
        mSelectedGender = MEN;
    }

    @Override
    public void onCreate() {
        loadCategories(mSelectedGender);

        if (mSelectedCategory == 0) {
            mView.openDrawer();
        }

        mView.toggleGenderButtons(mSelectedGender == MEN);
    }

    private void loadCategories(Gender gender) {
        boolean men = gender == MEN;
        mDataWrapper.loadCategories(men, this);
    }

    @Override
    public void onMenuWomenClicked() {
        if (mSelectedGender == WOMEN) {
            // We're already displaying those, nothing to do here.
            return;
        }

        mSelectedGender = WOMEN;
        loadCategories(mSelectedGender);
        mView.toggleGenderButtons(false);
    }

    @Override
    public void onMenuMenClicked() {
        if (mSelectedGender == MEN) {
            // We're already displaying those, nothing to do here.
            Log.d(TAG, "onMenuMenClicked: ALREADY here");
            return;
        }

        mSelectedGender = MEN;
        loadCategories(mSelectedGender);
        mView.toggleGenderButtons(true);
    }

    @Override
    public void onCategorySelected(long id) {
        mSelectedCategory = id;

        mView.onMenuItemSelected(id);
        mDataWrapper.loadProductsForCategory(id, this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(KEY_SELECTED_GENDER, mSelectedGender);
        outState.putLong(KEY_SELECTED_CATEGORY, mSelectedCategory);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        mSelectedGender = (Gender) savedInstanceState.getSerializable(KEY_SELECTED_GENDER);
        mSelectedCategory = savedInstanceState.getLong(KEY_SELECTED_CATEGORY, NOT_SET);

        boolean displayingMen = mSelectedGender == MEN;
        mView.toggleGenderButtons(displayingMen);

        loadCategories(mSelectedGender);

        if (mSelectedCategory != NOT_SET) {
            onCategorySelected(mSelectedCategory);
        }
    }

    @Override
    public void onProductSelected(long productId) {
        // Any validations come here ...
        mView.openSingleProductPage(productId);
    }

    @Override
    public void onProductToggleFavorites(long productId) {
        mDataWrapper.addToFavorites(productId);
        mView.onAddedToFavorites();
    }

    @Override
    public void onCategoriesLoaded(List<Category> categories) {
        mView.onCategoriesLoaded(categories);
    }

    @Override
    public void onDataError(String errorMessage) {
        // TODO: 10/26/2015 transform the "raw" error message to a more user-friendly one
        mView.onError(errorMessage);
    }

    @Override
    public void onProductsLoaded(List<Product> products) {
        mView.onProductsLoaded(products);
    }

    @Override
    public void onFavoritesClicked() {
        HashMap<Long, Product> favorites = mDataWrapper.getFavorites();
        mView.onDisplayFavorites(favorites);
    }

    @Override
    public void onBagClicked() {
        HashMap<Long, Integer> bag = mDataWrapper.getBag();
        mView.onDisplayBag(bag);
    }
}
