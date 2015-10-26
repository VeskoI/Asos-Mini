package com.veskoiliev.asosmini;

import com.veskoiliev.asosmini.model.pojo.CategoriesListing;
import com.veskoiliev.asosmini.model.pojo.ProductsListing;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface AsosService {

    @GET("/u/1559445/ASOS/SampleApi/cats_{gender}.json")
    Call<CategoriesListing> getCategories(@Path("gender") String gender);

    @GET("/u/1559445/ASOS/SampleApi/anycat_products.json")
    Call<ProductsListing> getProductsForCategory(@Query("catid") String categoryId);

    class Factory {
        public static AsosService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://dl.dropboxusercontent.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit.create(AsosService.class);
        }
    }
}
