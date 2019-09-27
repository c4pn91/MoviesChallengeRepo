package com.example.movieschallenge.ui.getMovies;

import android.content.Context;

import com.example.movieschallenge.R;
import com.example.movieschallenge.data.db.AppDbHelper;
import com.example.movieschallenge.data.model.ResponseMovies;
import com.example.movieschallenge.services.ApiClient;
import com.example.movieschallenge.services.ApiHelper;
import com.example.movieschallenge.utils.ConfigUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMoviesPresenter implements GetMoviesMVP.Presenter {

    private final String TAG = getClass().getSimpleName();

    private GetMoviesMVP.View view;
    private Context ctx;
    private ApiHelper apiHelper;
    private AppDbHelper database;

    public GetMoviesPresenter(GetMoviesMVP.View view, Context ctx) {
        this.view = view;
        this.ctx = ctx;
        apiHelper = ApiClient.getRetrofit().create(ApiHelper.class);
        database = AppDbHelper.getInstance(ctx);
    }

    @Override
    public void getMovies() {
        view.showProgressDialog();

        Call<ResponseMovies> call = apiHelper.getMovies(ConfigUtils.KEY, "popularity.desc",
                1);

        call.enqueue(new Callback<ResponseMovies>() {
            @Override
            public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
                view.hideProgressDialog();
                view.showMovies(response.body().getResults());
            }

            @Override
            public void onFailure(Call<ResponseMovies> call, Throwable t) {
                view.hideProgressDialog();
                getMoviesDb();
                view.error(t.getMessage());
            }
        });
    }

    @Override
    public void getMoviesDb() {
        if (database.getAllMovies() != null) {
            view.showMovies(database.getAllMovies());
        } else {
            view.error(ctx.getResources().getString(R.string.sorry_not_favs));
        }
    }

    @Override
    public void onDetach() {
        view = null;
    }
}
