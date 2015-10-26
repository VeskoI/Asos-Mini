package com.veskoiliev.asosmini.model.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import static com.veskoiliev.asosmini.model.db.Contract.AUTHORITY;

public class AsosContentProvider extends ContentProvider {

    // Possible database paths
    private static final String PATH_CATEGORY = Contract.Category.TABLE_NAME;
    private static final String PATH_PRODUCT = Contract.Product.TABLE_NAME;
    
    // Ids used for the UriMatcher
    private static final int ID_CATEGORIES_ALL = 10;
    private static final int ID_CATEGORY_SINGLE = 20;
    private static final int ID_CATEGORIES_MEN = 30;
    private static final int ID_CATEGORIES_WOMEN = 40;
    private static final int ID_PRODUCTS_ALL = 50;
    private static final int ID_PRODUCT_SINGLE = 60;

    private static final int ID_PRODUCTS_BY_CATEGORY = 70;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, PATH_CATEGORY, ID_CATEGORIES_ALL);
        sUriMatcher.addURI(AUTHORITY, PATH_CATEGORY + "/#", ID_CATEGORY_SINGLE);
        sUriMatcher.addURI(AUTHORITY, PATH_CATEGORY + "/men", ID_CATEGORIES_MEN);
        sUriMatcher.addURI(AUTHORITY, PATH_CATEGORY + "/women", ID_CATEGORIES_WOMEN);

        sUriMatcher.addURI(AUTHORITY, PATH_PRODUCT, ID_PRODUCTS_ALL);
        sUriMatcher.addURI(AUTHORITY, PATH_PRODUCT + "/#", ID_PRODUCT_SINGLE);
        sUriMatcher.addURI(AUTHORITY, PATH_PRODUCT + "/" + PATH_CATEGORY + "/#", ID_PRODUCTS_BY_CATEGORY);
    }

    public static Uri getUriCategories() {
        return Uri.parse("content://" + AUTHORITY + "/" + PATH_CATEGORY);
    }

    public static Uri getUriCategorySingle(long id) {
        return Uri.parse("content://" + AUTHORITY + "/" + PATH_CATEGORY + "/" + id);
    }

    public static Uri getUriCategoriesMen() {
        return Uri.parse("content://" + AUTHORITY + "/" + PATH_CATEGORY + "/men");
    }

    public static Uri getUriCategoriesWomen() {
        return Uri.parse("content://" + AUTHORITY + "/" + PATH_CATEGORY + "/women");
    }

    public static Uri getUriProducts() {
        return Uri.parse("content://" + AUTHORITY + "/" + PATH_PRODUCT);
    }

    public static Uri getUriProductsForCategory(long categoryDatabaseId) {
        return Uri.parse("content://" + AUTHORITY + "/" + PATH_PRODUCT + "/" + PATH_CATEGORY + "/" + categoryDatabaseId);
    }

    public static Uri getUriProductSingle(long id) {
        return Uri.parse("content://" + AUTHORITY + "/" + PATH_PRODUCT + "/" + id);
    }

    private DbOpenHelper mDatabaseHelper;

    @Override
    public boolean onCreate() {
        mDatabaseHelper = new DbOpenHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        Cursor cursor;

        int uriType = sUriMatcher.match(uri);
        switch (uriType) {
            case ID_CATEGORIES_ALL:
                queryBuilder.setTables(Contract.Category.TABLE_NAME);
                break;

            case ID_CATEGORY_SINGLE:
                queryBuilder.setTables(Contract.Category.TABLE_NAME);
                queryBuilder.appendWhere(Contract.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;

            case ID_CATEGORIES_MEN:
                queryBuilder.setTables(Contract.Category.TABLE_NAME);
                queryBuilder.appendWhere(Contract.Category.COLUMN_GENDER + "=" + Contract.Category.GENDER_MEN);
                break;

            case ID_CATEGORIES_WOMEN:
                queryBuilder.setTables(Contract.Category.TABLE_NAME);
                queryBuilder.appendWhere(Contract.Category.COLUMN_GENDER + "=" + Contract.Category.GENDER_WOMEN);
                break;

            case ID_PRODUCTS_ALL:
                queryBuilder.setTables(Contract.Product.TABLE_NAME);
                break;

            case ID_PRODUCT_SINGLE:
                queryBuilder.setTables(Contract.Product.TABLE_NAME);
                queryBuilder.appendWhere(Contract.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;

            case ID_PRODUCTS_BY_CATEGORY:
                queryBuilder.setTables(Contract.Product.TABLE_NAME);
                queryBuilder.appendWhere(Contract.Product.COLUMN_CATEGORY_DATABASE_ID + "=" + uri.getLastPathSegment());
                break;

            default:
                throw new IllegalArgumentException("Wrong query URI: " + uri);
        }

        cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);

        // make sure potential listeners are get notified
        if (getContext() != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        int uriType = sUriMatcher.match(uri);
        long id;

        // TODO: 10/26/2015 validate input fields

        switch (uriType) {
            case ID_CATEGORIES_ALL:
                id = db.insert(Contract.Category.TABLE_NAME, null, values);
                break;
            case ID_PRODUCTS_ALL:
                id = db.insert(Contract.Product.TABLE_NAME, null, values);
                break;
            default:
                throw new IllegalArgumentException("Wrong insert URI: " + uri);
        }

        if (getContext() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        switch (uriType) {
            case ID_CATEGORIES_ALL:
                return getUriCategorySingle(id);
            case ID_PRODUCTS_ALL:
                return getUriProductSingle(id);
            default:
                throw new IllegalArgumentException("Wrong insert URI: " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        int uriType = sUriMatcher.match(uri);
        int rowsDeleted;

        String id = uri.getLastPathSegment();
        if (TextUtils.isEmpty(id)) {
            throw new IllegalArgumentException("Wrong delete uri, not specified an ID to delete! (uri: " + uri);
        }

        switch (uriType) {
            case ID_PRODUCT_SINGLE:
                rowsDeleted = db.delete(Contract.Category.TABLE_NAME, Contract.COLUMN_ID + " = " + id, null);
                break;

            default:
                throw new IllegalArgumentException("Wrong delete URI: " + uri);
        }


        if (rowsDeleted > 0 && getContext() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        int uriType = sUriMatcher.match(uri);
        int rowsUpdated;

        String id = uri.getLastPathSegment();
        if (TextUtils.isEmpty(id)) {
            throw new IllegalArgumentException("Wrong update uri, not specified an ID to update! (uri: " + uri);
        }

        // TODO: 10/26/2015 validate data

        switch (uriType) {

            case ID_CATEGORY_SINGLE:
                rowsUpdated = db.update(
                        Contract.Category.TABLE_NAME,
                        values,
                        Contract.COLUMN_ID + " = " + id,
                        null);
                break;

            case ID_PRODUCT_SINGLE:
                rowsUpdated = db.update(
                        Contract.Product.TABLE_NAME,
                        values,
                        Contract.COLUMN_ID + " = " + id,
                        null);
                break;

            default:
                throw new IllegalArgumentException("Wrong update URI: " + uri);
        }

        if (rowsUpdated > 0 && getContext() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        int uriType = sUriMatcher.match(uri);
        int returnCount;

        switch (uriType) {
            case ID_CATEGORIES_ALL:
                returnCount = performBulkInsertTransaction(values, db, Contract.Category.TABLE_NAME);
                break;

            case ID_PRODUCTS_ALL:
                returnCount = performBulkInsertTransaction(values, db, Contract.Product.TABLE_NAME);
                break;

            default:
                return super.bulkInsert(uri, values);
        }

        if (getContext() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return returnCount;
    }

    private int performBulkInsertTransaction(ContentValues[] values, SQLiteDatabase db, String tableName) {
        int returnCount = 0;
        db.beginTransaction();
        try {
            for (ContentValues value : values) {
                long id = db.insert(tableName, null, value);
                if (id != -1) {
                    returnCount++;
                }
            }
            db.setTransactionSuccessful(); // this will commit the transaction
        } finally {
            db.endTransaction();
        }

        return returnCount;
    }
}
