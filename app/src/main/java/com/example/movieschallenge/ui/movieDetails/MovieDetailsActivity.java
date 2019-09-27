package com.example.movieschallenge.ui.movieDetails;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.movieschallenge.R;
import com.example.movieschallenge.data.model.Movie;
import com.example.movieschallenge.utils.ConfigUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsMVP.View {

    @BindView(R.id.image_button_goback_movie_details) ImageButton goBackMovie;
    @BindView(R.id.image_view_poster_movie_details) ImageView imageMovie;
    @BindView(R.id.text_view_title_movie_details) TextView titleMovie;
    @BindView(R.id.text_view_release_date_movie_details) TextView releaseMovie;
    @BindView(R.id.text_view_overview_movie_details) TextView overviewMovie;
    @BindView(R.id.ratingbar_average_movie_details) RatingBar averageMovie;
    @BindView(R.id.text_view_vote_count_movie_details) TextView votesMovie;
    @BindView(R.id.toogle_button_movie_details) ToggleButton toggleButton;

    Movie movie;
    MovieDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        handleIntent(getIntent());
        presenter = new MovieDetailsPresenter(this, this);
        loadViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.isFav(movie);
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
    }

    private void loadViews() {

        goBackMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        toggleButton.setText(null);
        toggleButton.setTextOn(null);
        toggleButton.setTextOff(null);
        toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),
                R.drawable.ic_favorite_border_gray_24dp));
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    presenter.insertFavMovie(movie);
                } else {
                    presenter.deleteFavMovie(movie);
                }
            }
        });
    }

    private void handleIntent(Intent i) {
        if (i != null) {
            Movie movie = (Movie) i.getParcelableExtra("movie");
            fillFields(movie);
        } else {
            // Info dialog ocurrió un error
            finish();
        }
    }

    private void fillFields(Movie movie) {
        this.movie = movie;

        Picasso.get().load(ConfigUtils.BASE_URL_IMAGES + movie.getPoster_path())
                .error(this.getResources().getDrawable(R.drawable.failconnection))
                .fit().centerCrop().into(imageMovie);

        titleMovie.setText(movie.getOriginal_title());
        releaseMovie.setText(movie.getRelease_date());
        overviewMovie.setText(movie.getOverview());
        averageMovie.setRating(movie.getVote_average());
        votesMovie.setText(movie.getVote_count() + " votos");
    }

    @Override
    public void isFav(boolean fav) {
        toggleButton.setChecked(fav);
        if (fav) {
            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),
                    R.drawable.ic_favorite_red_24dp));
        } else {
            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),
                    R.drawable.ic_favorite_border_gray_24dp));
        }
    }
}
