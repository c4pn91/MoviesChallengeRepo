package com.example.movieschallenge.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.movieschallenge.data.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class AppDbHelper implements DbHelper {

    private final String TAG = getClass().getSimpleName();

    private static AppDbHelper sInstance;
    private DbOpenHelper databaseOpenHelper = null;

    public static synchronized AppDbHelper getInstance(Context context) {
        if (sInstance == null)
            sInstance = new AppDbHelper(DbOpenHelper.getInstance(context));

        return sInstance;
    }

    public AppDbHelper(DbOpenHelper databaseOpenHelper) {
        this.databaseOpenHelper = databaseOpenHelper;
    }

    @Override
    public long insertMovie(Movie movie) {
        // get writable database as we want to write data
        SQLiteDatabase db = databaseOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        // no need to add them
        values.put(Movie.COLUMN_ID, movie.getId());
        values.put(Movie.COLUMN_POPULARITY, movie.getPopularity());
        values.put(Movie.COLUMN_VOTE_COUNT, movie.getVote_count());
        values.put(Movie.COLUMN_POSTER_PATH, movie.getPoster_path());
        values.put(Movie.COLUMN_ORIGINAL_LANGUAGE, movie.getOriginal_language());
        values.put(Movie.COLUMN_ORIGINAL_TITLE, movie.getOriginal_title());
        values.put(Movie.COLUMN_VOTE_AVERAGE, movie.getVote_average());
        values.put(Movie.COLUMN_OVERVIEW, movie.getOverview());
        values.put(Movie.COLUMN_RELEASE_DATE, movie.getRelease_date());

        // insert row
        long id = db.insert(Movie.TABLE_NAME, null, values);
        // close db connection
        db.close();
        // return newly inserted row id
        return id;
    }

    @Override
    public Movie getMovie(int id) {
        return null;
    }

    @Override
    public int deleteMovie(int id) {
        SQLiteDatabase db = databaseOpenHelper.getWritableDatabase();
        int i = db.delete(Movie.TABLE_NAME, Movie.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
        return i;
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> list = new ArrayList<>();
        SQLiteDatabase db = databaseOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + Movie.TABLE_NAME, null);

        int iId = cursor.getColumnIndex(Movie.COLUMN_ID);
        int iPopularity = cursor.getColumnIndex(Movie.COLUMN_POPULARITY);
        int iVoteCount = cursor.getColumnIndex(Movie.COLUMN_VOTE_COUNT);
        int iPosterPath = cursor.getColumnIndex(Movie.COLUMN_POSTER_PATH);
        int iOriginalLanguage = cursor.getColumnIndex(Movie.COLUMN_ORIGINAL_LANGUAGE);
        int iOriginalTitle = cursor.getColumnIndex(Movie.COLUMN_ORIGINAL_TITLE);
        int iVoteAverage = cursor.getColumnIndex(Movie.COLUMN_VOTE_AVERAGE);
        int iOverview = cursor.getColumnIndex(Movie.COLUMN_OVERVIEW);
        int iReleaseDate = cursor.getColumnIndex(Movie.COLUMN_RELEASE_DATE);

        cursor.moveToFirst();

        try {
            cursor.moveToFirst();

            do {
                int id = cursor.getInt(iId);
                int popularity = cursor.getInt(iPopularity);
                int voteCount = cursor.getInt(iVoteCount);
                String posterPath = cursor.getString(iPosterPath);
                String originalLanguage = cursor.getString(iOriginalLanguage);
                String originalTitle = cursor.getString(iOriginalTitle);
                int voteAverage = cursor.getInt(iVoteAverage);
                String overview = cursor.getString(iOverview);
                String releaseDate = cursor.getString(iReleaseDate);

                list.add(new Movie(id, popularity, voteCount, posterPath, originalLanguage,
                        originalTitle, voteAverage, overview, releaseDate));
            } while (cursor.moveToNext());

        } catch (Exception e) {
            return null;
        }

        cursor.close();
        db.close();

        return list;
    }
}
