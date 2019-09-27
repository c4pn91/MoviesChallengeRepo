package com.example.movieschallenge.ui.movieDetails;

import com.example.movieschallenge.data.model.Movie;

public interface MovieDetailsMVP {

    interface View {
        void isFav(boolean fav);
    }

    interface Presenter {
        void isFav(Movie movie);
        void insertFavMovie(Movie movie);
        void deleteFavMovie(Movie movie);
        void onDetach();
    }
}
