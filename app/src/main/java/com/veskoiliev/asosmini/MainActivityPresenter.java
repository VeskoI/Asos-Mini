package com.veskoiliev.asosmini;

public interface MainActivityPresenter {

    void refreshCategories(boolean men);

    void onMenuWomenClicked();

    void onMenuMenClicked();

    void onCategorySelected(int id);
}
