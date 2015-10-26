package com.veskoiliev.asosmini.ui;

import com.veskoiliev.asosmini.model.pojo.Category;

import java.util.List;

public interface DataFetchedListener {

    void onCategoriesLoaded(List<Category> categories);

    void onDataError(String errorMessage);
}
