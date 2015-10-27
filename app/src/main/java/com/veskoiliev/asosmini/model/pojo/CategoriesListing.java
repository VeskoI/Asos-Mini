package com.veskoiliev.asosmini.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Used to deserialize the response of {@link com.veskoiliev.asosmini.AsosService#getCategories(String)}.
 */
public class CategoriesListing {

    @SerializedName("Description")
    private String description;

    @SerializedName("Listing")
    private List<Category> categories;

    public String getDescription() {
        return description;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
