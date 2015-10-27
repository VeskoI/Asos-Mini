package com.veskoiliev.asosmini.dagger;

import android.content.ContentResolver;

import com.veskoiliev.asosmini.AsosMiniApp;
import com.veskoiliev.asosmini.AsosService;
import com.veskoiliev.asosmini.model.DataWrapper;
import com.veskoiliev.asosmini.model.DataWrapperImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger2 module that provides data-layer objects such as {@link AsosService}
 * and {@link DataWrapper}.
 */
@Module
public class DataModule {

    @Provides
    @Singleton
    DataWrapper provideDataWrapper() {
        return new DataWrapperImpl();
    }

    @Provides
    @Singleton
    AsosService provideAsosService() {
        return AsosService.Factory.create();
    }

    @Provides
    @Singleton
    ContentResolver provideContentResolver(AsosMiniApp app) {
        return app.getContentResolver();
    }

    @Provides
    @Singleton
    AsosMiniApp provideApplication() {
        return AsosMiniApp.getInstance();
    }
}
