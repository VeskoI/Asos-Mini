package com.veskoiliev.asosmini.model;

import com.veskoiliev.asosmini.ui.singleproduct.ProductLoadedListener;

public interface DataWrapper {
    void loadCategories(boolean men, DataFetchedListener listener);
    void loadProductsForCategory(long categoryDatabaseId, DataFetchedListener listener);
    void loadProduct(long productId, ProductLoadedListener listener);
}
