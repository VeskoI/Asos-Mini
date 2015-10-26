package com.veskoiliev.asosmini.model;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.util.Log;

import com.veskoiliev.asosmini.AsosMiniApp;
import com.veskoiliev.asosmini.AsosService;
import com.veskoiliev.asosmini.model.db.AsosContentProvider;
import com.veskoiliev.asosmini.model.db.ContentProviderHelper;
import com.veskoiliev.asosmini.model.pojo.CategoriesListing;
import com.veskoiliev.asosmini.model.pojo.Category;
import com.veskoiliev.asosmini.model.pojo.Product;
import com.veskoiliev.asosmini.ui.DataFetchedListener;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class DataWrapperImpl implements DataWrapper {

    private static final String TAG = "vesko";

    private ContentResolver mContentResolver;
    private AsosService mNetworkService;

    public DataWrapperImpl() {
        mContentResolver = AsosMiniApp.getInstance().getContentResolver();
        mNetworkService = AsosService.Factory.create();
    }


    @Override
    public void loadCategories(final boolean men, final DataFetchedListener listener) {
        List<Category> categories = ContentProviderHelper.getCategories(mContentResolver, men);
        if (!categories.isEmpty()) {
            listener.onCategoriesLoaded(categories);
        } else {
            fetchCategories(men, listener);
        }
    }

    private void fetchCategories(final boolean men, final DataFetchedListener listener) {
        AsosService asosService = AsosService.Factory.create();
        Call<CategoriesListing> call = asosService.getCategories("men");
        call.enqueue(new Callback<CategoriesListing>() {
            @Override
            public void onResponse(Response<CategoriesListing> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    CategoriesListing listing = response.body();

                    if (listing != null && listing.getCategories() != null) {
                        List<Category> categories = listing.getCategories();

                        // We have to update the gender info manually, since it doesn't come from the server.
                        for (Category c : categories) {
                            c.setGender(men);
                        }

                        ContentValues[] contentValues = new ContentValues[categories.size()];
                        for (int i = 0; i < categories.size(); i++) {
                            contentValues[i] = categories.get(i).getContentValues();
                        }

                        int rowsInserted = mContentResolver.bulkInsert(AsosContentProvider.getUriCategories(), contentValues);
                        Log.d(TAG, "___ rowsInserted: " + rowsInserted);

                        listener.onCategoriesLoaded(categories);
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

    @Override
    public List<Product> getProductsForCategory(String categoryId) {
        return null;
    }

    @Override
    public Product getProduct(long productId) {
        return null;
    }
}
