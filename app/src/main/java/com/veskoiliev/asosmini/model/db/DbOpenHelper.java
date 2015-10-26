package com.veskoiliev.asosmini.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "asosmini.db";
    private static final int DATABASE_VERSION = 1;

    public DbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Contract.Category.onCreate(db);
        Contract.Product.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Contract.Category.onUpgrade(db, oldVersion, newVersion);
        Contract.Product.onUpgrade(db, oldVersion, newVersion);
    }
}
