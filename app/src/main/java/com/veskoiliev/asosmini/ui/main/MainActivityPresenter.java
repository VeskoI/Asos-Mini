package com.veskoiliev.asosmini.ui.main;

import android.os.Bundle;

import com.veskoiliev.asosmini.ui.ActionBarPresenter;

public interface MainActivityPresenter extends ActionBarPresenter {

    void onCreate();

    void onMenuWomenClicked();

    void onMenuMenClicked();

    void onCategorySelected(long id);

    void onSaveInstanceState(Bundle outState);

    void onRestoreInstanceState(Bundle savedInstanceState);

    void onProductSelected(long productId);

    void onProductToggleFavorites(long productId);
}
