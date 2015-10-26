package com.veskoiliev.asosmini.ui.singleproduct;

import com.veskoiliev.asosmini.model.DataWrapper;
import com.veskoiliev.asosmini.model.DataWrapperImpl;
import com.veskoiliev.asosmini.model.pojo.ProductDetails;

public class SingleProductPresenterImpl implements SingleProductPresenter, ProductLoadedListener {

    private DataWrapper mDataWrapper;
    private SingleProductView mView;

    public SingleProductPresenterImpl(SingleProductView view) {
        mView = view;
        mDataWrapper = new DataWrapperImpl();
    }

    @Override
    public void onOpened(long productId) {
        mDataWrapper.loadProduct(productId, this);
    }

    @Override
    public void onProductDetailsLoaded(ProductDetails productDetails) {
        mView.onProductLoaded(productDetails);
    }

    @Override
    public void onProductFailedToLoad(String errorMessage) {
        // TODO: 10/26/2015 transform the "raw" errorMessage to a user-friendly one
        mView.onError(errorMessage);
    }
}
