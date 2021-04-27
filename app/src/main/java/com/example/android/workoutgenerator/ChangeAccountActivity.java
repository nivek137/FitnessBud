package com.example.android.workoutgenerator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ChangeAccountActivity extends AppCompatActivity {
    EditText mFirstName, mLastName, mWeight;
    Spinner mGenderSpinner, mAgeSpinner, mHeightSpinner;
    Button mButtonSave;
    DatabaseHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_account);

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

        mButtonSave = (Button) findViewById(R.id.button_save);
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fName = mFirstName.getText().toString().trim();
                String lName = mLastName.getText().toString().trim();
                String weight = mWeight.getText().toString().trim();
                String age = mAgeSpinner.getSelectedItem().toString();
                String height = mHeightSpinner.getSelectedItem().toString();
                String gender = mGenderSpinner.getSelectedItem().toString();
                boolean res = db.addInformation(username, fName, lName, weight,age, gender, height);
                if(res == true) {
                    Intent intent = new Intent(ChangeAccountActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ChangeAccountActivity.this,"Update Error",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
