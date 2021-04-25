package com.example.android.workoutgenerator.data;

import android.provider.BaseColumns;

/**
 * Created by cmccl on 3/9/2018.
 */

public class WorkoutContract {

    private WorkoutContract() {

    }

    public static final class WorkoutEntry implements BaseColumns {

        public final static String TABLE_NAME = "savedworkouts";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_WO_DESCRIPTION = "description";
        public final static String COLUMN_WO_RX = "rx";
        public final static String COLUMN_WO_TYPE = "type";
        public final static String COLUMN_WO_SAVE = "save";

        public final static int SAVE_NOT = 0;
        public final static int SAVE_IS = 1;

    }
}

