package com.veskoiliev.asosmini.model.db;

import android.database.sqlite.SQLiteDatabase;

public class Contract {

    // ContentProvider authority
    public static final String AUTHORITY = "com.veskoiliev.asosmini.db";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";

    public static final class Category {
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_CATEGORY_ID = "categoryId";
        public static final String COLUMN_PRODUCT_COUNT = "productCount";
        public static final String COLUMN_GENDER = "gender";

        private static final String SQL_CREATE_TABLE = "create table "
                + TABLE_NAME
                + "("
                + COLUMN_ID + " integer primary key autoincrement not null, "
                + COLUMN_NAME + " varchar not null, "
                + COLUMN_CATEGORY_ID + " varchar not null, "
                + COLUMN_PRODUCT_COUNT + " integer, "
                + COLUMN_GENDER + " bool "
                + ");";

        public static void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TABLE);
        }

        public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // // TODO: 10/26/2015 come up with an update strategy. For now just drop old table and recreate
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public static final class Product {
        public static final String TABLE_NAME = "product";
        public static final String COLUMN_PRODUCT_ID = "productId";
        public static final String COLUMN_CATEGORY_ID = "categoryId";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_BASE_PRICE = "basePrice";
        public static final String COLUMN_BRAND = "brand";
        public static final String COLUMN_CURRENT_PRICE = "currentPrice";
        public static final String COLUMN_HAS_MORE_COLORS = "hasMoreColors";
        public static final String COLUMN_IMAGE_URL = "imageUrl";

        // TODO: 10/26/2015 CATEGORY_ID should reference CATEGORY.categoryId
        private static final String SQL_CREATE_TABLE = "create table "
                + TABLE_NAME
                + "("
                + COLUMN_ID + " integer primary key autoincrement not null, "
                + COLUMN_TITLE + " varchar not null, "
                + COLUMN_PRODUCT_ID + " integer not null, "
                + COLUMN_CATEGORY_ID + " varchar not null, "
                + COLUMN_BASE_PRICE + " numeric, "
                + COLUMN_CURRENT_PRICE + " varchar, "
                + COLUMN_BRAND + " varchar, "
                + COLUMN_HAS_MORE_COLORS + " bool, "
                + COLUMN_IMAGE_URL + " varchar "
                + ");";

        public static void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TABLE);
        }

        public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // // TODO: 10/26/2015 come up with an update strategy. For now just drop old table and recreate
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

}
