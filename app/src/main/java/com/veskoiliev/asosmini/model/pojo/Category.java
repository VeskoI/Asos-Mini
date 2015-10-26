package com.veskoiliev.asosmini.model.pojo;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.veskoiliev.asosmini.model.db.Contract;

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

    private int gender;
    private int _id;

    public int getDatabaseId() {
        return _id;
    }

    @Override
    public String toString() {
        return categoryId + " - " + name + " - " + productCount;
    }

    public static Category fromCursor(Cursor cursor) {
        Category category = new Category();
        category._id = cursor.getInt(cursor.getColumnIndex(Contract.COLUMN_ID));
        category.name = cursor.getString(cursor.getColumnIndex(Contract.COLUMN_NAME));
        category.categoryId = cursor.getString(cursor.getColumnIndex(Contract.Category.COLUMN_CATEGORY_ID));
        category.productCount = cursor.getInt(cursor.getColumnIndex(Contract.Category.COLUMN_PRODUCT_COUNT));
        category.gender = cursor.getInt(cursor.getColumnIndex(Contract.Category.COLUMN_GENDER));
        return category;
    }

    public void setGender(boolean men) {
        this.gender = men ? Contract.Category.GENDER_MEN : Contract.Category.GENDER_WOMEN;
    }

    @NonNull
    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues(6);
        contentValues.put(Contract.COLUMN_NAME, name);
        contentValues.put(Contract.Category.COLUMN_CATEGORY_ID, categoryId);
        contentValues.put(Contract.Category.COLUMN_PRODUCT_COUNT, productCount);
        contentValues.put(Contract.Category.COLUMN_GENDER, gender);
        return contentValues;
    }
}
