package com.veskoiliev.asosmini;

import android.app.Application;
import android.support.annotation.NonNull;

import com.veskoiliev.asosmini.model.pojo.Category;

import java.util.List;

public class AsosMiniApp extends Application {

    private static AsosMiniApp instance;

    public static AsosMiniApp getInstance() {
        return instance;
    }

    private List<Category> categories;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @NonNull
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
