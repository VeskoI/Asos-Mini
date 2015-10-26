package com.veskoiliev.asosmini.ui.singleproduct;

import com.veskoiliev.asosmini.model.pojo.ProductDetails;

public interface SingleProductView {
    void onProductLoaded(ProductDetails productDetails);

    void onError(String errorMessage);
}
