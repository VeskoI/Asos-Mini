package com.veskoiliev.asosmini.model;

import com.veskoiliev.asosmini.model.pojo.Product;
import com.veskoiliev.asosmini.ui.singleproduct.ProductLoadedListener;

import java.util.HashMap;

public interface DataWrapper {
    void loadCategories(boolean men, DataFetchedListener listener);
    void loadProductsForCategory(long categoryDatabaseId, DataFetchedListener listener);
    void loadProduct(long productId, ProductLoadedListener listener);
    void addToFavorites(long productId);
    void addToBag(long productId);
    HashMap<Long, Product> getFavorites();
    HashMap<Long, Integer> getBag();
}
