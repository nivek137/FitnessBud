package com.example.android.workoutgenerator;

import java.io.Serializable;

/**
 * Created by cmccl on 3/7/2018.
 */

public class Workout implements Serializable {

    // Workout description is the full workout
    // Workout RX is the prescribed weight for men and women (if there is one)
    // Workout type denotes the type of workout. Use the following and add types as needed:
    //  - AMRAP (As many reps as possible)
    //  - FT (For Time)
    //  - EMOM (Every minute on the minute)
    //
    // Saved determines if the workout is a saved workout. 0 denotes no, 1 denotes yes. The constructors
    // set all 'saved' variables to 0 to start.
    String workoutDesc;
    String workoutRX;
    String type;
    int saved;


    //Made 2 constructors. The first one does not include an RX value
    public Workout(String description, String type, int saved) {
        this.workoutDesc = description;
        this.type = type;
        this.saved = saved;

    }
    // The second constructor here includes and RX weight
    public Workout(String description, String rx, String type, int saved){
        this.workoutDesc = description;
        this.workoutRX = rx;
        this.type = type;
        this.saved = saved;
    }

    public String getWorkoutDesc() {
        return workoutDesc;
    }

    public void setWorkoutDesc(String workoutDesc) {
        this.workoutDesc = workoutDesc;
    }

    public String getWorkoutRX() {
        return workoutRX;
    }

    public void setWorkoutRX(String workoutRX) {
        this.workoutRX = workoutRX;
    }

    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }

    public int getSaved() {
        return saved;
    }

    public void setSaved(int saved) {
        this.saved = saved;
    }
}
