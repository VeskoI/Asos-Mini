package com.veskoiliev.asosmini.model.pojo;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import com.veskoiliev.asosmini.model.db.Contract;

import java.util.List;

/**
 * Used both to deserialize the result from {@link com.veskoiliev.asosmini.AsosService#getProductsForCategory(String)},
 * but also provides helper methods to be used with our database.
 *
 * NOTE: This object has only the major info about a Product.
 * The object that represents all possible details is {@link ProductDetails}.
 */
public class Product {

    /**
     * Build a Product object from the row pointed by the Cursor.
     *
     * NOTE: This method does NOT close the Cursor!
     *
     * @param cursor pointing to the data that's used to build this Product
     * @return a Product object for this cursor
     */
    @NonNull
    public static Product fromCursor(@NonNull Cursor cursor) {
        Product product = new Product();
        product._id = cursor.getLong(cursor.getColumnIndex(Contract.COLUMN_ID));
        product.productId = cursor.getLong(cursor.getColumnIndex(Contract.Product.COLUMN_PRODUCT_ID));
        product.categoryDatabaseId = cursor.getLong(cursor.getColumnIndex(Contract.Product.COLUMN_CATEGORY_DATABASE_ID));
        product.currentPrice = cursor.getString(cursor.getColumnIndex(Contract.Product.COLUMN_CURRENT_PRICE));
        product.brand = cursor.getString(cursor.getColumnIndex(Contract.Product.COLUMN_BRAND));
        product.mainImageUrl = cursor.getString(cursor.getColumnIndex(Contract.Product.COLUMN_IMAGE_URL));
        product.basePrice = cursor.getDouble(cursor.getColumnIndex(Contract.Product.COLUMN_BASE_PRICE));
        product.title = cursor.getString(cursor.getColumnIndex(Contract.Product.COLUMN_TITLE));

        return product;
    }

    /** Database record id */
    private long _id;
    private long categoryDatabaseId;

    private String mainImageUrl;

    @SerializedName("BasePrice")
    private double basePrice;

    @SerializedName("Brand")
    private String brand;

    @SerializedName("CurrentPrice")
    private String currentPrice;

    /** External product id, as returned by a remote server for example */
    @SerializedName("ProductId")
    private long productId;

    @SerializedName("ProductImageUrl")
    private List<String> imageUrls;

    @SerializedName("Title")
    private String title;

    @Override
    public String toString() {
        return "_id: " + _id + ", productId: " + productId + ", title: " + title + ", mainImageUrl: " + getImageUrl();
    }

    /**
     * @return ContentValues object that contains all data, prepared to be inserted in our database.
     */
    @NonNull
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues(7);
        values.put(Contract.Product.COLUMN_PRODUCT_ID, productId);
        values.put(Contract.Product.COLUMN_CATEGORY_DATABASE_ID, categoryDatabaseId);
        values.put(Contract.Product.COLUMN_CURRENT_PRICE, currentPrice);
        values.put(Contract.Product.COLUMN_BRAND, brand);
        values.put(Contract.Product.COLUMN_IMAGE_URL, getImageUrl());
        values.put(Contract.Product.COLUMN_BASE_PRICE, basePrice);
        values.put(Contract.Product.COLUMN_TITLE, title);

        return values;
    }

    public void setCategoryDatabaseId(long categoryDatabaseId) {
        this.categoryDatabaseId = categoryDatabaseId;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    @Nullable
    public String getImageUrl() {
        if (mainImageUrl == null && imageUrls != null && !imageUrls.isEmpty()) {
            // For the sake of simplicity let's assume the first image in the array is the main one.
            mainImageUrl = imageUrls.get(0);
        }

        return mainImageUrl;
    }

    public long getId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }
}
