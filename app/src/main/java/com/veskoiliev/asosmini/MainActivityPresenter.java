package com.veskoiliev.asosmini;

import android.os.Bundle;

public interface MainActivityPresenter {

    void onCreate(boolean men);

    void onRecreate(boolean men);

    void onMenuWomenClicked();

    void onMenuMenClicked();

    void onCategorySelected(long id);

    void onSaveInstanceState(Bundle outState);

    void onRestoreInstanceState(Bundle savedInstanceState);
}
