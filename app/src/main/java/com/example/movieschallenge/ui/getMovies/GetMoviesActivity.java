package com.example.movieschallenge.ui.getMovies;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.movieschallenge.R;
import com.example.movieschallenge.data.db.AppDbHelper;
import com.example.movieschallenge.data.model.Movie;
import com.example.movieschallenge.ui.getMovies.adapters.MovieAdapter;
import com.example.movieschallenge.ui.movieDetails.MovieDetailsActivity;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;
import com.yarolegovich.lovelydialog.LovelyProgressDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetMoviesActivity extends AppCompatActivity implements GetMoviesMVP.View {

    @BindView(R.id.toolbar_movies) Toolbar toolbar;
    @BindView(R.id.recycler_view_movies) RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout_movies) SwipeRefreshLayout swipeRefreshLayout;

    // Presenter
    private GetMoviesPresenter presenter;

    // List Movies
    private List<Movie> moviesList = new ArrayList<>();

    // Adapter RecyclerView - Movie
    private MovieAdapter movieAdapter;

    // Dialogs
    LovelyProgressDialog progressDialog;
    LovelyInfoDialog infoDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_movies);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        bindingPresenter();
        loadViews();
        getMovies();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                presenter.getMoviesDb();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void bindingPresenter() {
        presenter = new GetMoviesPresenter(this, this);
    }

    private void loadViews() {
        // Build Dialogs
        progressDialog = new LovelyProgressDialog(this).setTitle(R.string.loading)
                .setMessage(R.string.load_movies);
        infoDialog = new LovelyInfoDialog(this).setTitle(R.string.info);

        // Build Adapter
        movieAdapter = new MovieAdapter(moviesList, this,
                new MovieAdapter.RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                Intent intent = new Intent(getApplicationContext(), MovieDetailsActivity.class);
                intent.putExtra("movie", moviesList.get(pos));
                startActivity(intent);
            }
        });

        // Build RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL));

        // Refresh
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        presenter.getMovies();
                    }
                }
        );
    }

    private void getMovies() {
        presenter.getMovies();
    }

    @Override
    public void showProgressDialog() {
        progressDialog.dismiss();
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void error(String error) {
        infoDialog.dismiss();
        infoDialog.setMessage(error).show();
    }

    @Override
    public void showMovies(List<Movie> movieList) {
        this.moviesList.clear();
        this.moviesList.addAll(movieList);
        movieAdapter.notifyDataSetChanged();
        if (swipeRefreshLayout.isRefreshing()) swipeRefreshLayout.setRefreshing(false);
    }
}
