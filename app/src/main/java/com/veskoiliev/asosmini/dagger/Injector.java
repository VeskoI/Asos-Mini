package com.veskoiliev.asosmini.dagger;

/**
 * Helper class that handles Dagger2 component creation.
 */
public class Injector {

    /** Dagger2 component */
    private static AsosComponent mComponent;

    /**
     * Build the Dagger2 component.
     *
     * NOTE: Modules with default-generated constructor do NOT have to be specified explicitly.
     * In this case it's not required to specify DataModule()
     */
    public static void init() {
        mComponent = DaggerAsosComponent.builder()
//                .dataModule(new DataModule())
                .build();
    }

    /**
     * @return {@link AsosComponent} that can be used to inject targets.
     * @throws IllegalStateException is the component isn't initialized.
     */
    public static AsosComponent getComponent() {
        if (mComponent == null) {
            throw new IllegalStateException("Component should be initialized before accessing it!");
        }
        return mComponent;
    }
}
