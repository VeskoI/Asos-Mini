package com.veskoiliev.asosmini.ui;

public interface SplashView {
    void showLoadingProgressBar();

    void startMainActivity();

    void displayError(String errorMessage);
}
