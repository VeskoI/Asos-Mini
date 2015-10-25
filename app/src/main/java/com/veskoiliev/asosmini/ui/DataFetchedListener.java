package com.veskoiliev.asosmini.ui;

public interface DataFetchedListener {

    void onDataLoaded();

    void onDataError(String errorMessage);
}
