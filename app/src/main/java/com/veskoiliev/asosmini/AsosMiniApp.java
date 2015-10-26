package com.veskoiliev.asosmini;

import android.app.Application;

public class AsosMiniApp extends Application {

    private static AsosMiniApp instance;

    public static AsosMiniApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
