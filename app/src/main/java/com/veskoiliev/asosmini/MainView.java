package com.veskoiliev.asosmini;

import com.veskoiliev.asosmini.model.pojo.Category;
import com.veskoiliev.asosmini.model.pojo.Product;

import java.util.List;

public interface MainView {
    void onCategoriesLoaded(List<Category> categories);

    void onProductsLoaded(List<Product> products);

    void onMenuItemSelected(long id);

    void openDrawer();

    void toggleGenderButtons(boolean displayingMen);
}
