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
    TextView mUsername,mFirstname,mLastname,mWeight,mAge,mGender,mHeight;;
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
        mFirstname = (TextView)findViewById(R.id.first_name);
        mLastname = (TextView)findViewById(R.id.last_name);
        mWeight =(TextView)findViewById(R.id.weight);
        mAge = (TextView)findViewById(R.id.age);
        mGender = (TextView)findViewById(R.id.gender);
        mHeight = (TextView)findViewById(R.id.height);
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
        Cursor res = db.viewData(username);
        String fname="",lname="";
        if(res.getCount()==0) {
            Toast.makeText(ProfileActivity.this,"No entry exists",Toast.LENGTH_SHORT).show();
            return;
        }
        while(res.moveToNext()) {
            if(res.getString(1).equals(username)) {
                System.out.println(res.getString(0));
                System.out.println(res.getString(1));
                System.out.println(res.getString(2));
                System.out.println(res.getString(3));
                System.out.println(res.getString(4));
                System.out.println(res.getString(5));
                System.out.println(res.getString(6));
                System.out.println(res.getString(7));
                System.out.println(res.getString(8));

                mUsername.setText(res.getString(1));
                mFirstname.setText(res.getString(3));
                mLastname.setText(res.getString(4));
                mAge.setText(res.getString(5));
                mWeight.setText(res.getString(6));
                mGender.setText(res.getString(7));
                mHeight.setText(res.getString(8));
            }
        }



    }

}
