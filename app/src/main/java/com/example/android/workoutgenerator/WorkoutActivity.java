package com.example.android.workoutgenerator;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.workoutgenerator.data.WorkoutDbHelper;
import com.example.android.workoutgenerator.data.WorkoutContract.WorkoutEntry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);


        /**
         * This creates the string that the user selected in the first equipment drop down menu
         */
        // The string is saved to selection1 variable
        Intent intent = getIntent();
        String equipSelection1 = intent.getStringExtra("EquipSelection1");
        String movementSelection1 = intent.getStringExtra("MovementSelection1");



        final ArrayList<Workout> workouts = new ArrayList<>();
        workouts.add(new Workout("5 rounds for time of:\n" + "Run 400 meters\n" + "15 thrusters", "Men: 95 lb.\nWomen: 65 lb.", "FT", 0));
        workouts.add(new Workout("15 min Thrusters AMRAP:\n10 Burpees\n10 Sit ups\10 Hand Release Push ups", "AMRAP", 0));
        workouts.add(new Workout (" 3 rounds for time of:\n" + "50 GHD sit-ups\n" + "25 Dumbbell curls and Thrusters", "FT", 0));
        workouts.add(new Workout ("3 Rounds For Time:\nRun 800m\n50 Air Squats", "FT", 0));
        workouts.add(new Workout ("10 Rounds For Time:\n10 Pushups\n10 Sit ups\n10 Squats ", "FT", 0));
        workouts.add(new Workout("For Time:\n200 Air Squats", "FT", 0));
        workouts.add(new Workout("5 Rounds For Time:\nRun 200m\n10 Squats\n10 Push Ups", "FT", 0));
        workouts.add(new Workout("AMRAP in 20 minutes:\n5 Pushups\n10 Situps\n15 Squats", "FT", 0));
        workouts.add(new Workout("10 min EMOM\nEven Minutes: 20 KB swings\nOdd minutes: 12 burpees", "EMOM", 0));
        workouts.add(new Workout("Fran:\n21-15-9\nThrusters\nPull-ups", "Men: 95 lbs.\nWomen: 65 lbs", "FT", 0 ));
        workouts.add(new Workout("For Time:\n" +
                "2 Minutes Double Unders\n" +
                "2 Minutes Situps\n" +
                "Rest 1 min\n" +
                "90 sec Double Unders\n" +
                "90 sec Situps\n" +
                "Rest 1 min\n" +
                "60 sec Double Unders\n" +
                "60 sec Situps", "FT", 0));
        workouts.add(new Workout("For Time:\n" +
                "60 Pushups\n" +
                "Run 400m\n" +
                "40 Pushups\n" +
                "Run 800m\n" +
                "20 Pushups\n" +
                "Run 1 mile", "FT", 0));


        // Need to figure out how to incorporate a databsae here


        // This creates a new database if needed or loads the existing database and makes it
        // writable.
//        WorkoutDbHelper mDbHelper = new WorkoutDbHelper(this);
//        SQLiteDatabase db = mDbHelper.getWritableDatabase();
//
//        for (int i = 0; i < workouts.size(); ++i){
//            String currentWorkoutDesc = workouts.get(i).getWorkoutDesc();
//            String currentWorkoutRx = workouts.get(i).getWorkoutRX();
//            String currentWorkoutType = workouts.get(i).getType();
//            int currentWorkoutSaved = workouts.get(i).getSaved();
//
//            // This is a sql command that will add the values to the specified columns in the database.
//            ContentValues values = new ContentValues();
//            values.put(WorkoutEntry.COLUMN_WO_DESCRIPTION, currentWorkoutDesc);
//            values.put(WorkoutEntry.COLUMN_WO_RX, currentWorkoutRx);
//            values.put(WorkoutEntry.COLUMN_WO_TYPE, currentWorkoutType);
//            values.put(WorkoutEntry.COLUMN_WO_SAVE, currentWorkoutSaved);
//
//            long newRowID = db.insert(WorkoutEntry.TABLE_NAME, null, values);
//        }





        /**
         * This removes any workout that does not contain the equipment in equipSelection1
         * and also removes any workouts that does not contain the movement in movementSelection1
         * If both variables are set to "Any", then the arraylist will not be filtered
         */
        if (!equipSelection1.equals("Any") && !movementSelection1.equals("Any")) {
            int i = 0;
            while (i < workouts.size()) {
                String currentWorkout = workouts.get(i).getWorkoutDesc();
                if (!currentWorkout.contains(equipSelection1) ||
                        !currentWorkout.contains(movementSelection1)) {
                    workouts.remove(i);
                } else {
                    ++i;
                }
            }
        }
        else if (equipSelection1.equals("Any") && !movementSelection1.equals("Any")){
            int i = 0;
            while (i < workouts.size()) {
                String currentWorkout = workouts.get(i).getWorkoutDesc();
                if (!currentWorkout.contains(movementSelection1)) {
                    workouts.remove(i);
                } else {
                    ++i;
                }
            }
        }
        else if (!equipSelection1.equals("Any") && movementSelection1.equals("Any")){
            int i = 0;
            while (i < workouts.size()) {
                String currentWorkout = workouts.get(i).getWorkoutDesc();
                if (!currentWorkout.contains(equipSelection1)) {
                    workouts.remove(i);
                } else {
                    ++i;
                }
            }
        }

        Collections.shuffle(workouts);


        WorkoutAdapter adapter = new WorkoutAdapter(this, workouts);

        GridView gridView = (GridView) findViewById(R.id.list);


        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(WorkoutActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(WorkoutActivity.this, DetailsActivity.class);
                Workout detailWorkout = workouts.get(position);
                intent.putExtra("Workout", detailWorkout);
                startActivity(intent);
            }
        });

    }

}


