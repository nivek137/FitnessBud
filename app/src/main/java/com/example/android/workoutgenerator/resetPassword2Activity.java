package com.example.android.workoutgenerator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class resetPassword2Activity extends AppCompatActivity {
    EditText etPw,etCfmPw;
    Button confirm;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_2);

        db = new DatabaseHelper(this);
        Intent i = getIntent();
        Bundle bundle = getIntent().getExtras();
        final String username = bundle.getString("username");
        etPw = (EditText)findViewById(R.id.pw);
        etCfmPw = (EditText)findViewById(R.id.cfm_pw);

        confirm = (Button)findViewById(R.id.btnConfirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password  = etPw.getText().toString().trim();
                String cfm_password  = etCfmPw.getText().toString().trim();

                if(password.equals(cfm_password)){
                    boolean update = db.changePassword(password,username);
                    if(update==true) {
                        Toast.makeText(resetPassword2Activity.this,"Password changed successfully",Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(resetPassword2Activity.this,HomeActivity.class);
                        startActivity(moveToLogin);
                    } else {
                        Toast.makeText(resetPassword2Activity.this,"Password change failed",Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(resetPassword2Activity.this,MainActivity.class);
                        startActivity(moveToLogin);
                    }

                }
                else{
                    Toast.makeText(resetPassword2Activity.this,"Password is not matching",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}