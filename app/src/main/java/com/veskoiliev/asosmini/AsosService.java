package com.veskoiliev.asosmini;

import com.veskoiliev.asosmini.model.pojo.CategoriesListing;
import com.veskoiliev.asosmini.model.pojo.ProductDetails;
import com.veskoiliev.asosmini.model.pojo.ProductsListing;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Network interface for communicating to ASOS server.
 *
 * NOTE: In this example it communicates to a fake Dropbox server.
 */
public interface AsosService {

    String GENDER_MEN = "men";
    String GENDER_WOMEN = "women";

    @GET("/u/1559445/ASOS/SampleApi/cats_{gender}.json")
    Call<CategoriesListing> getCategories(@Path("gender") String gender);

    @GET("/u/1559445/ASOS/SampleApi/anycat_products.json")
    Call<ProductsListing> getProductsForCategory(@Query("catid") String categoryId);

    @GET("u/1559445/ASOS/SampleApi/anyproduct_details.json")
    Call<ProductDetails> getProductDetails(@Query("catid") String productId);

    class Factory {
        private static final String BASE_URL = "https://dl.dropboxusercontent.com/";

        public static AsosService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit.create(AsosService.class);
        }
    }
}
