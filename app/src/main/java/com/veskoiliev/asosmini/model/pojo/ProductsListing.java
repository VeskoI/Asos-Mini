package com.veskoiliev.asosmini.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsListing {

    @SerializedName("ItemCount")
    private int itemCount;

    @SerializedName("Listings")
    private List<Product> products;

    @Override
    public String toString() {
        return "ItemCount: " + itemCount + ", products size: " + (products != null ? products.size() : "null");
    }

    public List<Product> getProducts() {
        return products;
    }
}
