package com.example.movieschallenge.services;

import com.example.movieschallenge.data.model.ResponseMovies;

import retrofit2.Call;

public class AppApiHelper implements ApiHelper {

    @Override
    public Call<ResponseMovies> getMovies(String apiKey, String sortBy, int numPage) {
        return null;
    }
}
