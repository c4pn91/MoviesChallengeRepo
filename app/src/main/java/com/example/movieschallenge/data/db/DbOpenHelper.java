package com.example.movieschallenge.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.movieschallenge.data.model.Movie;

public class DbOpenHelper extends SQLiteOpenHelper {

    // Tag
    private final String TAG = getClass().getSimpleName();

    // Instance DbOpenHelper
    private static DbOpenHelper mInstance = null;

    // Context
    private Context ctx;

    // Database version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "movies_db";

    public static synchronized DbOpenHelper getInstance(Context context) {
        if (mInstance == null)
            mInstance = new DbOpenHelper(context.getApplicationContext());

        return mInstance;
    }

    public DbOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Movie.CREATE_TABLE);
    }

    // Upgrading Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Movie.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }
}
