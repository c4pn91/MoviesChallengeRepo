package com.example.movieschallenge.ui.movieDetails;

import android.content.Context;

import com.example.movieschallenge.R;
import com.example.movieschallenge.data.db.AppDbHelper;
import com.example.movieschallenge.data.model.Movie;

import java.util.List;

public class MovieDetailsPresenter implements MovieDetailsMVP.Presenter {

    private MovieDetailsMVP.View view;
    private Context ctx;
    AppDbHelper database;

    public MovieDetailsPresenter(MovieDetailsMVP.View view, Context ctx) {
        this.view = view;
        this.ctx = ctx;
        database = AppDbHelper.getInstance(ctx);
    }

    @Override
    public void isFav(Movie movie) {
        List<Movie> listMovies = database.getAllMovies();
        if (listMovies != null) {
            for (Movie m : listMovies) {
                if (m.getId() == movie.getId()) {
                    view.isFav(true);
                    return;
                }
            }
        }

        view.isFav(false);
    }

    @Override
    public void insertFavMovie(Movie movie) {
        if (database.insertMovie(movie) >= 0) view.isFav(true);
    }

    @Override
    public void deleteFavMovie(Movie movie) {
        if (database.deleteMovie(movie.getId()) >= 0) view.isFav(false);
    }

    @Override
    public void onDetach() {
        view = null;
    }
}
