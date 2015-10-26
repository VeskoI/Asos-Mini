package com.veskoiliev.asosmini;

import com.veskoiliev.asosmini.model.DataWrapper;
import com.veskoiliev.asosmini.model.DataWrapperImpl;
import com.veskoiliev.asosmini.model.pojo.Category;
import com.veskoiliev.asosmini.ui.DataFetchedListener;

import java.util.List;

import static com.veskoiliev.asosmini.model.db.Contract.Category.GENDER_MEN;
import static com.veskoiliev.asosmini.model.db.Contract.Category.GENDER_WOMEN;

public class MainActivityPresenterImpl implements MainActivityPresenter, DataFetchedListener {

    private MainView mView;
    private DataWrapper mDataWrapper;
    private int mCurrentGender;

    public MainActivityPresenterImpl(MainView mainView) {
        mView = mainView;
        mDataWrapper = new DataWrapperImpl();
        mCurrentGender = GENDER_MEN;
    }

    @Override
    public void refreshCategories(boolean men) {
        mDataWrapper.loadCategories(men, this);
    }

    @Override
    public void onMenuWomenClicked() {
        if (mCurrentGender == GENDER_WOMEN) {
            // We're already displaying those, nothing to do here.
            return;
        }

        refreshCategories(true);
    }

    @Override
    public void onMenuMenClicked() {
        if (mCurrentGender == GENDER_MEN) {
            // We're already displaying those, nothing to do here.
            return;
        }

        refreshCategories(false);
    }

    @Override
    public void onCategorySelected(int id) {
        // TODO: 10/26/2015 implement
    }

    @Override
    public void onCategoriesLoaded(List<Category> categories) {
        mView.onCategoriesLoaded(categories);
    }

    @Override
    public void onDataError(String errorMessage) {

    }
}
