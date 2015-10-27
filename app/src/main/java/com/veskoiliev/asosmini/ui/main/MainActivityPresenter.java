package com.veskoiliev.asosmini.ui.main;

import android.os.Bundle;

import com.veskoiliev.asosmini.ui.ActionBarPresenter;

public interface MainActivityPresenter extends ActionBarPresenter {

    void onResume();

    void onMenuWomenClicked();

    void onMenuMenClicked();

    void onCategorySelected(long id);

    void onSaveInstanceState(Bundle outState);

    void onRestoreInstanceState(Bundle savedInstanceState);

    void onProductSelected(long productId);

    /**
     * TODO for the sake of this example we can only ADD items to favorites, but we cannot REMOVE them.
     * @param productId external (non-database) productId to be added to favorites.
     */
    void onProductToggleFavorites(long productId);
}
