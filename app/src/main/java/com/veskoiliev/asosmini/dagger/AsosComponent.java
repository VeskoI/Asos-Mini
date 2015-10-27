package com.veskoiliev.asosmini.dagger;

import com.veskoiliev.asosmini.model.DataWrapperImpl;
import com.veskoiliev.asosmini.ui.main.MainActivityPresenterImpl;
import com.veskoiliev.asosmini.ui.singleproduct.SingleProductPresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Main Dagger2 component.
 *
 * NOTE: remember that Dagger2 is strictly types, so it needs to list all injected objects here.
 */
@Singleton
@Component(
        modules = {
                DataModule.class
        }
)
public interface AsosComponent {
    void inject(MainActivityPresenterImpl mainPresenter);
    void inject(SingleProductPresenterImpl singleProductPresenter);
    void inject(DataWrapperImpl dataWrapper);
}
