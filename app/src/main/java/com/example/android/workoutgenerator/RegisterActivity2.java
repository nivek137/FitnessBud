package com.example.android.workoutgenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.workoutgenerator.data.WorkoutDbHelper;

public class RegisterActivity2 extends AppCompatActivity {
    EditText mFirstName, mLastName, mWeight;
    Spinner mGenderSpinner, mAgeSpinner, mHeightSpinner;
    Button mButtonSubmit;
    DatabaseHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        Intent i = getIntent();
        Bundle bundle = getIntent().getExtras();
        final String username = bundle.getString("username");

        db = new DatabaseHelper(this);
        mFirstName = (EditText) findViewById(R.id.first_name);
        mLastName = (EditText) findViewById(R.id.last_name);
        mWeight = (EditText) findViewById(R.id.weight);

        final Spinner mAgeSpinner = (Spinner) findViewById(R.id.age_spinner);
        final Spinner mHeightSpinner = (Spinner) findViewById(R.id.height_spinner);
        final Spinner mGenderSpinner = (Spinner) findViewById(R.id.gender_spinner);
        ArrayAdapter ageArrayAdaptor = ArrayAdapter.createFromResource(this,
                R.array.age_array, R.layout.spinner_item);

        ArrayAdapter heightArrayAdaptor = ArrayAdapter.createFromResource(this,
                R.array.height_array, R.layout.spinner_item);

        ArrayAdapter genderArrayAdaptor = ArrayAdapter.createFromResource(this,
                R.array.gender_array, R.layout.spinner_item);

        ageArrayAdaptor.setDropDownViewResource(R.layout.spinner_dropdown_item);
        genderArrayAdaptor.setDropDownViewResource(R.layout.spinner_dropdown_item);
        heightArrayAdaptor.setDropDownViewResource(R.layout.spinner_dropdown_item);

        mAgeSpinner.setAdapter(ageArrayAdaptor);
        mGenderSpinner.setAdapter(genderArrayAdaptor);
        mHeightSpinner.setAdapter(heightArrayAdaptor);

        mButtonSubmit = (Button) findViewById(R.id.button_register2);
        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fName = mFirstName.getText().toString().trim();
                String lName = mLastName.getText().toString().trim();
                String weight = mWeight.getText().toString().trim();
                String age = mAgeSpinner.getSelectedItem().toString();
                String height = mHeightSpinner.getSelectedItem().toString();
                String gender = mGenderSpinner.getSelectedItem().toString();
                System.out.println("Register Activity 2 b4 addInfo");
                boolean res = db.addInformation(username, fName, lName, weight,age, gender, height);
                System.out.println("Register Act 2 after addInfo");
                if(res == true) {
                    Intent intent = new Intent(RegisterActivity2.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity2.this,"Registration Error",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    }