package com.veskoiliev.asosmini.model;

import com.veskoiliev.asosmini.model.pojo.Product;
import com.veskoiliev.asosmini.ui.DataFetchedListener;

import java.util.List;

public interface DataWrapper {
    void loadCategories(boolean men, DataFetchedListener listener);
    List<Product> getProductsForCategory(String categoryId);
    Product getProduct(long productId);
}
