package com.veskoiliev.asosmini.model;

import com.veskoiliev.asosmini.model.pojo.Product;
import com.veskoiliev.asosmini.ui.DataFetchedListener;

public interface DataWrapper {
    void loadCategories(boolean men, DataFetchedListener listener);
    void loadProductsForCategory(long categoryDatabaseId, DataFetchedListener listener);
    Product getProduct(long productId);
}
