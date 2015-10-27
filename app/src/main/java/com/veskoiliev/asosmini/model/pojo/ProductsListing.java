package com.veskoiliev.asosmini.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Used to deserialize the reponse from {@link com.veskoiliev.asosmini.AsosService#getProductDetails(String)}.
 */
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
