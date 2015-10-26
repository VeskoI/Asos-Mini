package com.veskoiliev.asosmini.model.pojo;

public class Product {

    /**
     * + COLUMN_ID + " integer primary key autoincrement not null, "
     + COLUMN_TITLE + " varchar not null, "
     + COLUMN_PRODUCT_ID + " integer not null, "
     + COLUMN_CATEGORY_ID + " varchar not null, "
     + COLUMN_BASE_PRICE + " numeric, "
     + COLUMN_CURRENT_PRICE + " varchar, "
     + COLUMN_BRAND + " varchar, "
     + COLUMN_HAS_MORE_COLORS + " bool, "
     + COLUMN_IMAGE_URL + " varchar "
     */

    private long _id;
    private long productId;
    private String categoryId;
    private String currentPrice;
    private String brand;
    private String imageUrl;
    private double basePrice;
    private boolean hasMoreColors;
}
