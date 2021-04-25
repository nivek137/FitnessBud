package com.example.android.workoutgenerator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class resetPasswordActivity extends AppCompatActivity {

    EditText editTextUsername;
    Button reset;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        editTextUsername = (EditText)findViewById(R.id.cfm_pw);
        reset = (Button)findViewById(R.id.button_reset_pw);
        db = new DatabaseHelper(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString();
                Boolean checkUser = db.checkUsername(username);
                if(checkUser == true) {
                    Intent intent = new Intent(resetPasswordActivity.this,resetPassword2Activity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(resetPasswordActivity.this,"Username does not exist",Toast.LENGTH_SHORT).show();
                    editTextUsername.setError("Invalid username");
                }
            }
        });
    }
}
