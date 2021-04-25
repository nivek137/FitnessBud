package com.example.android.workoutgenerator;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.workoutgenerator.data.WorkoutDbHelper;

import com.example.android.workoutgenerator.data.WorkoutContract;
import com.example.android.workoutgenerator.data.WorkoutContract.WorkoutEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SavedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);


        //Calling the databaseInfo function will open the saved workout database and populate it
        // with all workouts that have been saved. It will also set up the gridview and create
        // an on click listener for when a user clicks on a workout to see the details.
        databaseInfo();



    }


    private void databaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        WorkoutDbHelper mDbHelper = new WorkoutDbHelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM workouts"
        // to get a Cursor that contains all rows from the pets table.
        String[] projection = {
                WorkoutEntry._ID,
                WorkoutEntry.COLUMN_WO_DESCRIPTION,
                WorkoutEntry.COLUMN_WO_RX,
                WorkoutEntry.COLUMN_WO_TYPE,
                WorkoutEntry.COLUMN_WO_SAVE
        };

        Cursor cursor = db.query(
                WorkoutEntry.TABLE_NAME,
                projection, null, null, null, null, null
        );

        cursor.moveToFirst();


        final ArrayList<Workout> workouts = new ArrayList<>();


        // After reading in the database. I set the cursor to the first item in the list and created
        // a new array list. Then the code below will iterate through the rows in the database
        // and populate the arraylist.
        try {
            for (int i = 0; i < cursor.getCount(); ++i){
                int descColumnIndex = cursor.getColumnIndex(WorkoutEntry.COLUMN_WO_DESCRIPTION);
                String desc = cursor.getString(descColumnIndex);
                int rxColumnIndex = cursor.getColumnIndex(WorkoutEntry.COLUMN_WO_RX);
                String rx = cursor.getString(rxColumnIndex);
                int typeColumnIndex = cursor.getColumnIndex(WorkoutEntry.COLUMN_WO_TYPE);
                String type = cursor.getString(typeColumnIndex);
                int savedColumnIndex = cursor.getColumnIndex(WorkoutEntry.COLUMN_WO_SAVE);
                int saved = cursor.getInt(savedColumnIndex);

                if (saved == 1){
                    Workout currentWorkout = new Workout(desc, rx, type, saved);
                    workouts.add(currentWorkout);
                }

                // increment cursor to the next line
                cursor.moveToNext();
            }


            WorkoutAdapter adapter = new WorkoutAdapter(this, workouts);

            GridView gridView = (GridView) findViewById(R.id.saved_list);


            gridView.setAdapter(adapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    //Toast.makeText(WorkoutActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SavedActivity.this, DetailsActivity.class);
                    Workout detailWorkout = workouts.get(position);
                    intent.putExtra("Workout", detailWorkout);
                    startActivity(intent);
                }
            });

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }


}
