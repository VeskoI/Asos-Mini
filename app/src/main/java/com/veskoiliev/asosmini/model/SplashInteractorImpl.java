package com.veskoiliev.asosmini.model;

import com.veskoiliev.asosmini.AsosMiniApp;
import com.veskoiliev.asosmini.AsosService;
import com.veskoiliev.asosmini.model.pojo.CategoriesListing;
import com.veskoiliev.asosmini.ui.DataFetchedListener;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class SplashInteractorImpl implements SplashInteractor {

    @Override
    public void loadCategories(final DataFetchedListener listener) {
        AsosService asosService = AsosService.Factory.create();
        Call<CategoriesListing> call = asosService.getCategories("men");
        call.enqueue(new Callback<CategoriesListing>() {
            @Override
            public void onResponse(Response<CategoriesListing> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    CategoriesListing categoriesListing = response.body();

                    if (categoriesListing != null && categoriesListing.getCategories() != null) {
                        AsosMiniApp.getInstance().setCategories(categoriesListing.getCategories());
                        listener.onDataLoaded();
                        return;
                    }
                }

                listener.onDataError(response.message());
            }

            @Override
            public void onFailure(Throwable t) {
                listener.onDataError(t.getMessage());
            }
        });
    }
}
