package com.example.uitmsa_sport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText email;
    private EditText password;
    private EditText cpassword; // confirm password
    private Button registerbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Firebase Authentication instance
        auth = FirebaseAuth.getInstance();

        // Bind UI elements to variables
        email = findViewById(R.id.inputemailreg);
        password = findViewById(R.id.inputpassreg);
        cpassword = findViewById(R.id.inputcpassreg);
        registerbtn = findViewById(R.id.registerbtn);

        // Set click listener for register button
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        // Obtain user inputs
        String userEmail = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();
        String confirmPassword = cpassword.getText().toString().trim();

        // Check if passwords match
        if (!userPassword.equals(confirmPassword)) {
            Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Register the user with Firebase Authentication
        auth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                        // Navigate to AboutActivity on successful registration
                        Intent intent = new Intent(RegisterActivity.this, AboutActivity.class);
                        startActivity(intent);
                        finish(); // Close the current activity to prevent going back on back press
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();

                        // Navigate to MainActivity on registration failure
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Close the current activity to prevent going back on back press
                    }
                });
    }
}
