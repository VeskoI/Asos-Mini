package com.veskoiliev.asosmini.ui.main;

/**
 * Simple listener that receives user-actions from the list of Products in {@link MainActivity#mRecyclerView}.
 */
public interface ProductSelectListener {
    /**
     * Triggered when an item is clicked.
     *
     * @param productId of the clicked item
     */
    void onProductSelected(long productId);

    /**
     * Triggered on long-click.
     *
     * @param productId to be added to favorites.
     */
    void addProductToFavorites(long productId);
}
