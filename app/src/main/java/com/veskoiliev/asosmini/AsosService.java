package com.veskoiliev.asosmini;

import com.veskoiliev.asosmini.model.pojo.CategoriesListing;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;

public interface AsosService {

    @GET("/u/1559445/ASOS/SampleApi/cats_{gender}.json")
    Call<CategoriesListing> getCategories(@Path("gender") String gender);

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
