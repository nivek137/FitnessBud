package com.example.android.workoutgenerator.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.workoutgenerator.data.WorkoutContract.WorkoutEntry;

/**
 * Created by cmccl on 3/9/2018.
 */

/**
 * Database helper for Workout app. Manages database creation and version management.
 */

public class WorkoutDbHelper extends SQLiteOpenHelper{

    public final static String LOG_TAG = WorkoutDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "savedworkouts.db";

    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link WorkoutDbHelper}.
     *
     * @param context of the app
     */
    public WorkoutDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_WODS_TABLE =  "CREATE TABLE " + WorkoutEntry.TABLE_NAME + " ("
                + WorkoutEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WorkoutEntry.COLUMN_WO_DESCRIPTION + " TEXT NOT NULL, "
                + WorkoutEntry.COLUMN_WO_RX + " TEXT DEFAULT NULL, "
                + WorkoutEntry.COLUMN_WO_TYPE + " TEXT, "
                + WorkoutEntry.COLUMN_WO_SAVE + " INTEGER NOT NULL DEFAULT 0);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_WODS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }



}
