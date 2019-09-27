package com.example.movieschallenge.services;

import com.example.movieschallenge.utils.ConfigUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(ConfigUtils.BASE_URL_GET_MOVIES)
                    .build();
        }

        return retrofit;
    }
}
