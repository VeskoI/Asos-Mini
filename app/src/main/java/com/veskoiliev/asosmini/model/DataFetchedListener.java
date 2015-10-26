package com.veskoiliev.asosmini.model;

import com.veskoiliev.asosmini.model.pojo.Category;
import com.veskoiliev.asosmini.model.pojo.Product;

import java.util.List;

public interface DataFetchedListener {

    void onCategoriesLoaded(List<Category> categories);

    void onDataError(String errorMessage);

    void onProductsLoaded(List<Product> products);
}
