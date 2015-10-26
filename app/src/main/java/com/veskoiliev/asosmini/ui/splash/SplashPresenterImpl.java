package com.veskoiliev.asosmini.ui.splash;

import com.veskoiliev.asosmini.model.DataWrapperImpl;
import com.veskoiliev.asosmini.model.pojo.Category;
import com.veskoiliev.asosmini.model.pojo.Product;
import com.veskoiliev.asosmini.model.DataFetchedListener;

import java.util.List;

public class SplashPresenterImpl implements SplashPresenter, DataFetchedListener {

    private final SplashView mView;
    private final DataWrapperImpl mDataWrapper;

    public SplashPresenterImpl(SplashView splashView) {
        mView = splashView;
        mDataWrapper = new DataWrapperImpl();
    }

    @Override
    public void onActivityCreated() {
        mView.showLoadingProgressBar();
        mDataWrapper.loadCategories(true, this);
    }

    @Override
    public void onCategoriesLoaded(List<Category> categories) {
        mView.startMainActivity();
    }

    @Override
    public void onDataError(String errorMessage) {
        mView.displayError(errorMessage);
    }

    @Override
    public void onProductsLoaded(List<Product> products) {

    }
}
