package com.veskoiliev.asosmini.model.pojo;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("CategoryId")
    private String categoryId;

    @SerializedName("Name")
    private String name;

    @SerializedName("ProductCount")
    private int productCount;

    public String getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return categoryId + " - " + name + " - " + productCount;
    }
}
