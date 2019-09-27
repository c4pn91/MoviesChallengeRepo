package com.example.movieschallenge.data.db;

import com.example.movieschallenge.data.model.Movie;

import java.util.List;

public interface DbHelper {

    // Insert Movie
    long insertMovie(Movie movie);

    // Get Movie
    Movie getMovie(int id);

    // Delete Movie
    int deleteMovie(int id);

    // Get Movies
    List<Movie> getAllMovies();
}
