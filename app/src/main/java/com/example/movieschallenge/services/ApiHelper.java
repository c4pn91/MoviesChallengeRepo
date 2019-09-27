package com.example.movieschallenge.services;

import com.example.movieschallenge.data.model.ResponseMovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiHelper {

    // Get movies
    @GET("movie")
    Call<ResponseMovies>
    getMovies(@Query("api_key") String apiKey, @Query("sort_by") String sortBy,
                       @Query("page") int numPage);
}
