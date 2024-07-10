package com.example.uitmsa_sport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText email;
    private EditText password;
    private Button loginbtn;
    private TextView regisbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Authentication instance
        auth = FirebaseAuth.getInstance();

        // Bind UI elements to variables
        email = findViewById(R.id.inputemaillogin);
        password = findViewById(R.id.inputpasswordlogin);
        loginbtn = findViewById(R.id.loginbtn);
        regisbtn = findViewById(R.id.regisbtn);

        // Set click listener for login button
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        // Set click listener for register button
        regisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        // Obtain user inputs
        String userEmail = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();

        // Sign in user with Firebase Authentication
        auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                        // Navigate to AboutActivity on successful login
                        Intent intent = new Intent(LoginActivity.this, AboutActivity.class);
                        startActivity(intent);
                        finish(); // Close the current activity to prevent going back on back press
                    } else {
                        // If sign in fails, display a message to the user
                        Toast.makeText(LoginActivity.this, "Login Failed. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
