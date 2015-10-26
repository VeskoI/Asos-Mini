package com.veskoiliev.asosmini.ui.singleproduct;

import com.veskoiliev.asosmini.model.DataWrapper;
import com.veskoiliev.asosmini.model.DataWrapperImpl;
import com.veskoiliev.asosmini.model.pojo.Product;
import com.veskoiliev.asosmini.model.pojo.ProductDetails;

import java.util.HashMap;

public class SingleProductPresenterImpl implements SingleProductPresenter, ProductLoadedListener {

    private DataWrapper mDataWrapper;
    private SingleProductView mView;

    private ProductDetails mCurrentProduct;

    public SingleProductPresenterImpl(SingleProductView view) {
        mView = view;
        mDataWrapper = new DataWrapperImpl();
    }

    @Override
    public void onOpened(long productId) {
        mDataWrapper.loadProduct(productId, this);
    }

    @Override
    public void onAddToBagClicked() {
        mDataWrapper.addToBag(mCurrentProduct.getProductId());
        mView.confirmProductAddedToBag();
    }

    @Override
    public void onProductDetailsLoaded(ProductDetails productDetails) {
        mCurrentProduct = productDetails;
        mView.onProductLoaded(productDetails);
    }

    @Override
    public void onProductFailedToLoad(String errorMessage) {
        // TODO: 10/26/2015 transform the "raw" errorMessage to a user-friendly one
        mView.onError(errorMessage);
    }

    @Override
    public void onFavoritesClicked() {
        HashMap<Long, Product> favorites = mDataWrapper.getFavorites();
        mView.onDisplayFavorites(favorites);
    }

    @Override
    public void onBagClicked() {
        HashMap<Long, Integer> bag = mDataWrapper.getBag();
        mView.onDisplayBag(bag);
    }
}
