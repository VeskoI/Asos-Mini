package com.veskoiliev.asosmini.ui.singleproduct;

import com.veskoiliev.asosmini.ui.ActionBarPresenter;

public interface SingleProductPresenter extends ActionBarPresenter {
    void onOpened(long productId);

    void onAddToBagClicked();
}

