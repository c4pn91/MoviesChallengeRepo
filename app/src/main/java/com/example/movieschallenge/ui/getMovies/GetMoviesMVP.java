package com.example.movieschallenge.ui.getMovies;

import com.example.movieschallenge.data.model.Movie;

import java.util.List;

public interface GetMoviesMVP {

    interface View {
        void showProgressDialog();
        void hideProgressDialog();
        void error(String error);
        void showMovies(List<Movie> movieList);
    }

    interface Presenter {
        void getMovies();
        void getMoviesDb();
        void onDetach();
    }

}
