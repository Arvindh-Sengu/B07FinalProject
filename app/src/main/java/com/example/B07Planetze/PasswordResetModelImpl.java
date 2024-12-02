package com.example.B07Planetze;

import android.util.Patterns;

import com.google.firebase.auth.FirebaseAuth;

public class PasswordResetModelImpl implements PasswordResetModel {

    // Reference to the view
    private final PasswordResetPresenter view;
    // Firebase authentication instance for handling user accounts
    private final FirebaseAuth mAuth;

    // Constructor to initialize the view and FirebaseAuth instance
    public PasswordResetModelImpl(PasswordResetPresenter view) {
        this.view = view;
        this.mAuth = FirebaseAuth.getInstance();
    }

    // Connects to firebase and uses it to send a password reset email to the user allowing
    // them to change their password through firebase
    @Override
    public void handlePasswordReset(String email) {
        if (email.isEmpty()) {
            view.showError("Please enter your email");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.showError("Please enter a valid email address");
            return;
        }

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        view.showSuccess("Password reset email sent");
                    } else {
                        view.showError("Error: " + task.getException().getMessage());
                    }
                });
    }
}
