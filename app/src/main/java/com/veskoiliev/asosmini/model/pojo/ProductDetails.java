package com.veskoiliev.asosmini.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Used to deserialize the response from {@link com.veskoiliev.asosmini.AsosService#getProductDetails(String)}.
 *
 * This object captures all possible info about a Product, so it has much more data than {@link Product},
 * which is a much, much simpler.
 */
public class ProductDetails {

    @SerializedName("BasePrice")
    private double basePrice;

    @SerializedName("Brand")
    private String brand;

    @SerializedName("CurrentPrice")
    private String currentPrice;

    @SerializedName("ProductId")
    private long productId;

    @SerializedName("ProductImageUrls")
    private List<String> imageUrls;

    @SerializedName("Title")
    private String title;

    @SerializedName("Description")
    private String description;

    @Override
    public String toString() {
        return productId + " -> " + title + ", images: " + (imageUrls != null ? imageUrls.size() : "null");
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public long getProductId() {
        return productId;
    }

    public long getId() {
        return productId;
    }

    public String getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getImages() {
        return imageUrls;
    }
}
