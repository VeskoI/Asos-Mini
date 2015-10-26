package com.veskoiliev.asosmini.ui.main;

import com.veskoiliev.asosmini.model.pojo.Category;
import com.veskoiliev.asosmini.model.pojo.Product;

import java.util.HashMap;
import java.util.List;

public interface MainView {
    void onCategoriesLoaded(List<Category> categories);

    void onProductsLoaded(List<Product> products);

    void onMenuItemSelected(long id);

    void openDrawer();

    void toggleGenderButtons(boolean displayingMen);

    void openSingleProductPage(long productId);

    void onError(String errorMessage);

    void onDisplayFavorites(HashMap<Long, Product> favorites);

    void onDisplayBag(HashMap<Long, Integer> bag);

    void onAddedToFavorites();
}
