package com.veskoiliev.asosmini.model.db;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.veskoiliev.asosmini.model.pojo.Category;
import com.veskoiliev.asosmini.model.pojo.Product;

import java.util.ArrayList;
import java.util.List;

public class ContentProviderHelper {

    @NonNull
    public static List<Category> getCategories(ContentResolver contentResolver, boolean men) {
        List<Category> result = new ArrayList<>(10);
        Uri uri = men ? AsosContentProvider.getUriCategoriesMen() : AsosContentProvider.getUriCategoriesWomen();
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor == null) {
            return result;
        }

        try {
            while (cursor.moveToNext()) {
                result.add(Category.fromCursor(cursor));
            }
        } finally {
            cursor.close();
        }

        return result;
    }

    @NonNull
    public static List<Product> getProductsForCategory(ContentResolver contentResolver, long categoryDatabaseId) {
        List<Product> result = new ArrayList<>(10);
        Uri uri = AsosContentProvider.getUriProductsForCategory(categoryDatabaseId);
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor == null) {
            return result;
        }

        try {
            while (cursor.moveToNext()) {
                result.add(Product.fromCursor(cursor));
            }
        } finally {
            cursor.close();
        }

        return result;
    }

    @Nullable
    public static String getCategoryIdByDatabaseId(ContentResolver contentResolver, long categoryDatabaseId) {
        String result = null;

        Uri uri = AsosContentProvider.getUriCategorySingle(categoryDatabaseId);
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                result = cursor.getString(cursor.getColumnIndex(Contract.Category.COLUMN_CATEGORY_ID));
            }
            cursor.close();
        }

        return result;
    }

    public static Product getProductById(ContentResolver contentResolver, long productId) {
        Product result = null;

        Uri uri = AsosContentProvider.getUriProducts();
        String selection = Contract.Product.COLUMN_PRODUCT_ID + "= ?";
        String[] selectionArgs = {String.valueOf(productId)};
        Cursor cursor = contentResolver.query(uri, null, selection, selectionArgs, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                result = Product.fromCursor(cursor);
            }
            cursor.close();
        }

        return result;

    }
}
