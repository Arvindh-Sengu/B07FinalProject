package com.example.B07Planetze;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.appcompat.app.AppCompatActivity;
import com.B07Planetze.R;

public class PasswordResetActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_password_reset);

        // Initialize mAuth
        mAuth = FirebaseAuth.getInstance();

        EditText emailEditText = findViewById(R.id.emailEditText);
        Button sendEmailButton = findViewById(R.id.sendEmailButton);
        TextView openLogin = findViewById(R.id.openLogin);

        sendEmailButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();

            if (email.isEmpty()) {
                Toast.makeText(PasswordResetActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
            } else {
                sendPasswordResetEmail(email);
            }
        });

        // Navigate back to Login screen
        openLogin.setOnClickListener(v -> {
            Intent intent = new Intent(PasswordResetActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void sendPasswordResetEmail(String email) {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(PasswordResetActivity.this, "Password reset email sent", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(PasswordResetActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
