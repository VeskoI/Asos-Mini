package com.veskoiliev.asosmini;

import com.veskoiliev.asosmini.model.pojo.Category;

import java.util.List;

public interface MainView {
    void onCategoriesLoaded(List<Category> categories);
}
