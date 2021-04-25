package com.example.android.workoutgenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mTextUsername,mTextPassword;
    Button mButtonLogin, mButtonCreateAcc, mButtonForgotPw;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.username);
        mTextPassword = (EditText)findViewById(R.id.cfm_pw);
        mButtonForgotPw = (Button)findViewById(R.id.btnForgotPassword);
        mButtonLogin = (Button)findViewById(R.id.btnConfirm);
        mButtonCreateAcc = (Button)findViewById(R.id.btnCreateAccount);
        mButtonCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


        // user clicks login button
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String password = mTextPassword.getText().toString().trim();
                Boolean res = db.checkUser(user,password);
                if(res==true) {
                    Toast.makeText(MainActivity.this,"Succesfully Logged in",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this,"Login Error",Toast.LENGTH_SHORT).show();
                    mTextPassword.setError("Invalid login credentials");
                }
            }
        });


        // user clicks forgot password button
        mButtonForgotPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent (MainActivity.this,resetPasswordActivity.class);
                startActivity(registerIntent);

            }
        });


    }
}
