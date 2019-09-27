package com.example.movieschallenge.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    // TAG
    private final String TAG = getClass().getSimpleName();

    // Table name
    public static final String TABLE_NAME = "movies_table";

    // Table column names
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_POPULARITY = "popularity";
    public static final String COLUMN_VOTE_COUNT = "vote_count";
    public static final String COLUMN_POSTER_PATH = "poster_path";
    public static final String COLUMN_ORIGINAL_LANGUAGE = "original_language";
    public static final String COLUMN_ORIGINAL_TITLE = "original_title";
    public static final String COLUMN_VOTE_AVERAGE = "vote_average";
    public static final String COLUMN_OVERVIEW = "overview";
    public static final String COLUMN_RELEASE_DATE = "release_date";

    private int id;
    private double popularity;
    private double vote_count;
    private String poster_path;
    private String original_language;
    private String original_title;
    private float vote_average;
    private String overview;
    private String release_date;

    public Movie() {}

    public Movie(int id, double popularity, double vote_count, String poster_path,
                 String original_language, String original_title, float vote_average,
                 String overview, String release_date) {
        this.setId(id);
        this.setPopularity(popularity);
        this.setVote_count(vote_count);
        this.setPoster_path(poster_path);
        this.setOriginal_language(original_language);
        this.setOriginal_title(original_title);
        this.setVote_average(vote_average);
        this.setOverview(overview);
        this.setRelease_date(release_date);
    }

    // Create table SQL query
    public static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_POPULARITY + " INTEGER, "
            + COLUMN_VOTE_COUNT + " INTEGER, "
            + COLUMN_POSTER_PATH + " TEXT, "
            + COLUMN_ORIGINAL_LANGUAGE + " TEXT, "
            + COLUMN_ORIGINAL_TITLE + " TEXT, "
            + COLUMN_VOTE_AVERAGE + " INTEGER, "
            + COLUMN_OVERVIEW + " TEXT, "
            + COLUMN_RELEASE_DATE + " TEXT"
            + ")";

    protected Movie(Parcel in) {
        setId(in.readInt());
        setPopularity(in.readDouble());
        setVote_count(in.readDouble());
        setPoster_path(in.readString());
        setOriginal_language(in.readString());
        setOriginal_title(in.readString());
        setVote_average(in.readFloat());
        setOverview(in.readString());
        setRelease_date(in.readString());
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId());
        dest.writeDouble(getPopularity());
        dest.writeDouble(getVote_count());
        dest.writeString(getPoster_path());
        dest.writeString(getOriginal_language());
        dest.writeString(getOriginal_title());
        dest.writeFloat(getVote_average());
        dest.writeString(getOverview());
        dest.writeString(getRelease_date());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public Double getVote_count() {
        return vote_count;
    }

    public void setVote_count(Double vote_count) {
        this.vote_count = vote_count;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
