package com.example.B07Planetze;

import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountModelImpl implements CreateAccountModel {

    // Reference to the view
    private final CreateAccountView view;
    // Firebase authentication instance for handling user accounts
    private final FirebaseAuth mAuth;

    // Constructor to initialize the view and FirebaseAuth instance
    public CreateAccountModelImpl(CreateAccountView view) {
        this.view = view;
        this.mAuth = FirebaseAuth.getInstance();
    }

    // create account logic using firebase
    @Override
    public void createAccount(String email, String password, String confirmPassword, String fullName) {
        // Check that all fields are filled out
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            view.showError("Please fill all fields");
            return;
        }

        // Check that passwords match
        if (!password.equals(confirmPassword)) {
            view.showError("Passwords do not match");
            return;
        }

        // Attempt to create account using Firebase
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        view.showSuccess("Account created successfully!");
                        view.navigateToHomeScreen();
                    } else {
                        view.showError("Error: " + task.getException().getMessage());
                    }
                });
    }
}
