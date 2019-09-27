package com.example.movieschallenge.ui.getMovies.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.movieschallenge.R;
import com.example.movieschallenge.data.model.Movie;
import com.example.movieschallenge.utils.ConfigUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

// Adapter that is used for RecyclerView
public class MovieAdapter extends Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movieList;
    private Context ctx;
    private RecyclerViewOnItemClickListener clickListener;

    public MovieAdapter(List<Movie> movieList, Context ctx,
                        RecyclerViewOnItemClickListener clickListener) {
        this.movieList = movieList;
        this.ctx = ctx;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_movie, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder viewHolder, int i) {

        Movie movie = movieList.get(i);

        Picasso.get().load(ConfigUtils.BASE_URL_IMAGES + movie.getPoster_path())
                .error(ctx.getResources().getDrawable(R.drawable.ic_new_releases_red_36dp))
                .into(viewHolder.poster_movie);

        viewHolder.title_movie.setText(movie.getOriginal_title());
        viewHolder.date_movie.setText("Release: " + movie.getRelease_date());
        viewHolder.language_movie.setText("Language: " + movie.getOriginal_language());
        viewHolder.average_movie.setRating(movie.getVote_average());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public interface RecyclerViewOnItemClickListener {
        void onClick(View view, int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.circle_image_view_poster_movie) CircleImageView poster_movie;
        @BindView(R.id.text_view_title_movie) TextView title_movie;
        @BindView(R.id.text_view_date_movie) TextView date_movie;
        @BindView(R.id.text_view_language_movie) TextView language_movie;
        @BindView(R.id.ratingbar_average_movie) RatingBar average_movie;

        View view;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }
}
