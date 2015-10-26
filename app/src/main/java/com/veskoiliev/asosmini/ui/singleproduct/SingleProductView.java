package com.veskoiliev.asosmini.ui.singleproduct;

import com.veskoiliev.asosmini.model.pojo.Product;
import com.veskoiliev.asosmini.model.pojo.ProductDetails;

import java.util.HashMap;

public interface SingleProductView {
    void onProductLoaded(ProductDetails productDetails);

    void onError(String errorMessage);

    void onDisplayFavorites(HashMap<Long, Product> favorites);

    void onDisplayBag(HashMap<Long, Integer> bag);

    void confirmProductAddedToBag();
}
