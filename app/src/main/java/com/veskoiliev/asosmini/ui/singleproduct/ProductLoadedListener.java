package com.veskoiliev.asosmini.ui.singleproduct;

import com.veskoiliev.asosmini.model.pojo.ProductDetails;

public interface ProductLoadedListener {
    void onProductDetailsLoaded(ProductDetails productDetails);
    void onProductFailedToLoad(String errorMessage);
}
