package com.example.android.workoutgenerator;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.workoutgenerator.data.WorkoutContract;
import com.example.android.workoutgenerator.data.WorkoutContract.WorkoutEntry;
import com.example.android.workoutgenerator.data.WorkoutDbHelper;

public class DetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        Intent i = getIntent();
        Workout detailWorkout = (Workout)i.getSerializableExtra("Workout");

        EditText score = (EditText) findViewById(R.id.score_field);
        String woType = detailWorkout.getType();

        if (woType.equals("FT")){
            score.setInputType(InputType.TYPE_CLASS_DATETIME);
        }
        else if (woType.equals("AMRAP")){
            score.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        else if (woType.equals("EMOM")){
            score.setInputType(InputType.TYPE_CLASS_TEXT);

        }
        else{
            score.setInputType(InputType.TYPE_CLASS_TEXT);
        }



        TextView desc = (TextView) findViewById(R.id.fulldescription);
        desc.setText(detailWorkout.getWorkoutDesc());

        TextView rx = (TextView) findViewById(R.id.rx);
        rx.setText(detailWorkout.getWorkoutRX());

        int saved = detailWorkout.getSaved();

        // if the workout is not a saved workout -> show the save button
        // if the workout is a saved workout -> show the 'remove' button
        if (saved == 0){
            //do nothing because saved is already set to visible
        }
        else {
            Button save_button = (Button) findViewById(R.id.save_button);
            save_button.setVisibility(View.GONE);

            Button remove_button = (Button) findViewById(R.id.remove_button);
            remove_button.setVisibility(View.VISIBLE);
        }

        //This makes sure the keyboard doesn't pop up until a user clicks an edit text field
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    public void saveWorkout(View view){

        // This is gathering information about the workout that was clicked
        Intent i = getIntent();
        Workout detailWorkout = (Workout)i.getSerializableExtra("Workout");
        String desc = detailWorkout.getWorkoutDesc();
        String rx = detailWorkout.getWorkoutRX();
        String type = detailWorkout.getType();


        // This sets the selected workouts 'saved' variable to true which is 1
        detailWorkout.setSaved(1);
        int saved = detailWorkout.getSaved();


        // This creates a new database if needed or loads the existing database and makes it
        // writable.
        WorkoutDbHelper mDbHelper = new WorkoutDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // This is a sql command that will add the values to the specified columns in the database.
        ContentValues values = new ContentValues();
        values.put(WorkoutEntry.COLUMN_WO_DESCRIPTION, desc);
        values.put(WorkoutEntry.COLUMN_WO_RX, rx);
        values.put(WorkoutEntry.COLUMN_WO_TYPE, type);
        values.put(WorkoutEntry.COLUMN_WO_SAVE, saved);



        // This takes the values above and adds them to the database
        long newRowID = db.insert(WorkoutEntry.TABLE_NAME, null, values);

        if (newRowID == -1){
            Toast.makeText(this, "Error with saving workout", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Workout saved with row id: " + newRowID, Toast.LENGTH_SHORT).show();
        }

        //set the save button to invisible and make the remove workout button visible
        Button save_button = (Button) findViewById(R.id.save_button);
        save_button.setVisibility(View.GONE);

        Button remove_button = (Button) findViewById(R.id.remove_button);
        remove_button.setVisibility(View.VISIBLE);


    }

    public void removeWorkout(View view){

        // This is gathering information about the workout that was clicked
        Intent i = getIntent();
        Workout detailWorkout = (Workout)i.getSerializableExtra("Workout");
        String desc = detailWorkout.getWorkoutDesc();
        String rx = detailWorkout.getWorkoutRX();
        String type = detailWorkout.getType();

        // This sets the selected workouts 'saved' variable to true which is 1
        detailWorkout.setSaved(1);
        int saved = detailWorkout.getSaved();


        // This creates a new database if needed or loads the existing database and makes it
        // writable.
        WorkoutDbHelper mDbHelper = new WorkoutDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();


        //Deletes all workouts that have the same description as the one selected by the user
        String whereClause = WorkoutEntry.COLUMN_WO_DESCRIPTION + "=?";
        String[] whereArgs = new String[] {desc};

        db.delete(WorkoutEntry.TABLE_NAME, whereClause, whereArgs);

        //set the remove button to invisible and make the save workout button visible
        Button save_button = (Button) findViewById(R.id.save_button);
        save_button.setVisibility(View.VISIBLE);

        Button remove_button = (Button) findViewById(R.id.remove_button);
        remove_button.setVisibility(View.GONE);

    }

    public void addNewWorkout(View view){

    }
}
