package com.veskoiliev.asosmini.ui.splash;

public interface SplashView {
    void showLoadingProgressBar();

    void startMainActivity();

    void displayError(String errorMessage);
}
