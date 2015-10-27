package com.veskoiliev.asosmini.model;

import android.support.annotation.NonNull;

import com.veskoiliev.asosmini.model.pojo.Product;
import com.veskoiliev.asosmini.ui.singleproduct.ProductLoadedListener;

import java.util.HashMap;

/**
 * Wraps our Data layer. Provides high level methods for retrieving data,
 * totally encapsulating the data sources it uses.
 */
public interface DataWrapper {
    /**
     * Load all categories for the provided gender. Search all internal data sources
     * and do our best to fetch those if needed.
     *
     * NOTE: Calling this method might trigger a network call!
     *
     * @param men gender to provide categories for. MEN if true, WOMEN if false
     * @param listener callback to receive the categories or an error message if not successful
     */
    void loadCategories(boolean men, DataFetchedListener listener);

    /**
     * Load all Products for a specific category, searching all internal data sources.
     * At this point we get only the basic info for a Product, such as Title, Price and Main Picture.
     * To load all details about a product, use {@link #loadProduct(long, ProductLoadedListener)}.
     *
     * NOTE: Calling this method might trigger a network call!
     *
     * @param categoryDatabaseId local database category ID (as stored in our {@link com.veskoiliev.asosmini.model.db.AsosContentProvider}
     * @param listener callback to receive the products or an error message if not successful
     */
    void loadProductsForCategory(long categoryDatabaseId, DataFetchedListener listener);

    /**
     * Load all details for the Product. Whereas {@link #loadProductsForCategory(long, DataFetchedListener)}
     * loads just the basic info, this one gives you a {@link com.veskoiliev.asosmini.model.pojo.ProductDetails}
     * object that wraps all info imaginable.
     *
     * NOTE: Calling this method might trigger a network call!
     *
     * TODO for the sake of this example the results are NOT cached anywhere, so this call ALWAYS triggers a network call!
     *
     * @param productId product ID, as returned by the {@link com.veskoiliev.asosmini.AsosService}.
     * @param listener callback to receive the resulting {@link com.veskoiliev.asosmini.model.pojo.ProductDetails}
     *                 or an error message if not successful
     */
    void loadProduct(long productId, ProductLoadedListener listener);

    /**
     * @param productId the "external" (non-database) productId to be added to favorites.
     */
    void addToFavorites(long productId);

    /**
     * Add a product to the Shopping bag.
     *
     * @param productId the "external" (non-database) productId to be added to shopping bag.
     */
    void addToBag(long productId);

    /**
     * @return HashMap containing all Favorites.
     */
    @NonNull
    HashMap<Long, Product> getFavorites();

    /**
     * @return HashMap containing all Shopping Bag items.
     */
    @NonNull
    HashMap<Long, Integer> getBag();
}
