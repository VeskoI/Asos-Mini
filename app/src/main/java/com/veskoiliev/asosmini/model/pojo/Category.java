package com.veskoiliev.asosmini.model.pojo;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.veskoiliev.asosmini.model.db.Contract;

/**
 * Used to deserialize the response of {@link com.veskoiliev.asosmini.AsosService#getCategories(String)}.
 *
 * It also provides handy methods for working with our Database.
 */
public class Category {

    /**
     * Build a Category object from the row pointed by the Cursor.
     *
     * NOTE: This method does NOT close the Cursor!
     *
     * @param cursor pointing to the data that's used to build this Category
     * @return a Category object for this cursor
     */
    @NonNull
    public static Category fromCursor(@NonNull Cursor cursor) {
        Category category = new Category();
        category._id = cursor.getLong(cursor.getColumnIndex(Contract.COLUMN_ID));
        category.name = cursor.getString(cursor.getColumnIndex(Contract.COLUMN_NAME));
        category.categoryId = cursor.getString(cursor.getColumnIndex(Contract.Category.COLUMN_CATEGORY_ID));
        category.productCount = cursor.getInt(cursor.getColumnIndex(Contract.Category.COLUMN_PRODUCT_COUNT));
        category.gender = cursor.getInt(cursor.getColumnIndex(Contract.Category.COLUMN_GENDER));
        return category;
    }

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

    private long _id;

    public long getDatabaseId() {
        return _id;
    }

    @Override
    public String toString() {
        return categoryId + " - " + name + " - " + productCount;
    }

    public void setGender(boolean men) {
        this.gender = men ? Contract.Category.GENDER_MEN : Contract.Category.GENDER_WOMEN;
    }

    /**
     * @return ContentValues object that contains all data, prepared to be inserted in our database.
     */
    @NonNull
    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues(4);
        contentValues.put(Contract.COLUMN_NAME, name);
        contentValues.put(Contract.Category.COLUMN_CATEGORY_ID, categoryId);
        contentValues.put(Contract.Category.COLUMN_PRODUCT_COUNT, productCount);
        contentValues.put(Contract.Category.COLUMN_GENDER, gender);
        return contentValues;
    }
}
