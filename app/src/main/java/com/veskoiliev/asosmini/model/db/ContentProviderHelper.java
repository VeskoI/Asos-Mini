package com.veskoiliev.asosmini.model.db;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.veskoiliev.asosmini.model.pojo.Category;

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
            while(cursor.moveToNext()) {
                result.add(Category.fromCursor(cursor));
            }
        } finally {
            cursor.close();
        }

        return result;
    }
}
