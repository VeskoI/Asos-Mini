package com.veskoiliev.asosmini;

import android.app.Application;

import com.veskoiliev.asosmini.dagger.Injector;

public class AsosMiniApp extends Application {

    private static AsosMiniApp instance;

    public static AsosMiniApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        // Prepare Dagger2 graph.
        Injector.init();
    }
}
