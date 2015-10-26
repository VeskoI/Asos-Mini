package com.veskoiliev.asosmini.ui.main;

public interface ProductSelectListener {
    void onProductSelected(long productId);

    void addProductToFavorites(long productId);
}
