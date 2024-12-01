package com.example.B07Planetze;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.B07Planetze.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccountActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_create_account);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();

        // UI Set Up
        EditText nameEditText = findViewById(R.id.nameEnterEditText);
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        EditText confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        Button createAccountButton = findViewById(R.id.createAccountButton);
        TextView openLogin = findViewById(R.id.openLogin);

        // Create Account Button Meat i.e. get info and run if good
        createAccountButton.setOnClickListener(v -> {
            String fullName = nameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String confirmPassword = confirmPasswordEditText.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(CreateAccountActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(CreateAccountActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                createFirebaseAccount(email, password, fullName);
            }
        });

        // Open Login Screen on click of navigation shtuff
        openLogin.setOnClickListener(v -> {
            Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void createFirebaseAccount(String email, String password, String fullName) {
        // user fire base auth to create account which will check for duplicate emails and stuff
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Account created goodly
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(CreateAccountActivity.this, "Account created successfully!", Toast.LENGTH_SHORT).show();

                        // Open Home Screen After Success
                        Intent intent = new Intent(CreateAccountActivity.this, HomeScreenActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Account creation failed so send error
                        Toast.makeText(CreateAccountActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
