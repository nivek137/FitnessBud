package com.example.android.workoutgenerator;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    TextView mUsername;
    EditText mFirstname,mLastname,mWeight,mAge,mGender,mHeight;
    DatabaseHelper db;
    String fName,lName,weight,age,gender,height;
    Button btnCancel,btnChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent i = getIntent();
        Bundle bundle = getIntent().getExtras();
        final String username = bundle.getString("username");

        mUsername = (TextView)findViewById(R.id.username);
        mFirstname = (EditText)findViewById(R.id.first_name);
        mLastname = (EditText)findViewById(R.id.last_name);
        mWeight = (EditText)findViewById(R.id.weight);
        mAge = (EditText)findViewById(R.id.age);
        mGender = (EditText)findViewById(R.id.gender);
        mHeight = (EditText)findViewById(R.id.height);
        db = new DatabaseHelper(this);

        setText(username);

        btnCancel = (Button)findViewById(R.id.button_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnChange = (Button)findViewById(R.id.button_change_profile);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,ChangeAccountActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
    }

    public void setText(String username) {
        Cursor res = db.viewData();
        if(res.getCount()==0) {
            Toast.makeText(ProfileActivity.this,"No entry exists",Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();

        while(res.moveToNext()) {

        }

    }

}
