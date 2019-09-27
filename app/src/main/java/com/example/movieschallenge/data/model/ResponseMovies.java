package com.example.movieschallenge.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ResponseMovies implements Parcelable {

    private int page;
    private int total_results;
    private int total_pages;
    private List<Movie> results;

    public ResponseMovies() {}

    public ResponseMovies(int page, int total_results, int total_pages, List<Movie> results) {
        this.page = page;
        this.total_results = total_results;
        this.total_pages = total_pages;
        this.results = results;
    }

    protected ResponseMovies(Parcel in) {
        setPage(in.readInt());
        setTotal_results(in.readInt());
        setTotal_pages(in.readInt());
        setResults(in.createTypedArrayList(Movie.CREATOR));
    }

    public static final Creator<ResponseMovies> CREATOR = new Creator<ResponseMovies>() {
        @Override
        public ResponseMovies createFromParcel(Parcel in) {
            return new ResponseMovies(in);
        }

        @Override
        public ResponseMovies[] newArray(int size) {
            return new ResponseMovies[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getPage());
        dest.writeInt(getTotal_results());
        dest.writeInt(getTotal_pages());
        dest.writeTypedList(getResults());
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
