package com.veskoiliev.asosmini.model;

import com.veskoiliev.asosmini.model.pojo.Product;

public interface DataWrapper {
    void loadCategories(boolean men, DataFetchedListener listener);
    void loadProductsForCategory(long categoryDatabaseId, DataFetchedListener listener);
    Product getProduct(long productId);
}
