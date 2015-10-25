package com.veskoiliev.asosmini.ui;

import com.veskoiliev.asosmini.model.SplashInteractor;
import com.veskoiliev.asosmini.model.SplashInteractorImpl;

public class SplashPresenterImpl implements SplashPresenter, DataFetchedListener {

    private final SplashView mView;
    private final SplashInteractor mInteractor;

    public SplashPresenterImpl(SplashView splashView) {
        mView = splashView;
        mInteractor = new SplashInteractorImpl();
    }

    @Override
    public void onActivityCreated() {
        mView.showLoadingProgressBar();
        mInteractor.loadCategories(this);
    }

    @Override
    public void onDataLoaded() {
        mView.startMainActivity();
    }

    @Override
    public void onDataError(String errorMessage) {
        mView.displayError(errorMessage);
    }
}
