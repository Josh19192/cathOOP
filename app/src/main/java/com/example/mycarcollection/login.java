package com.example.mycarcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText user,pass;
    Button login_button;

    private static final String VALID_USERNAME = "cath";
    private static final String VALID_PASSWORD = "cath123";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        user = findViewById(R.id.etxt_user);
        pass = findViewById(R.id.etxt_pass);
        login_button = findViewById(R.id.login_button);


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString();
                String password = pass.getText().toString();
                if (isValidCredentials(username, password)) {
                    // Successful login, you can navigate to another activity or perform other actions
                    Toast.makeText(login.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(login.this, "Invalid user or password", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    private boolean isValidCredentials(String username, String password) {
        // Compare with hardcoded credentials
        return username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD);
    }
}