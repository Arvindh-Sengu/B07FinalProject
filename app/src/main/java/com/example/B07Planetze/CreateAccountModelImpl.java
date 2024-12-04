package com.example.B07Planetze;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccountModelImpl implements CreateAccountModel {

    // Reference to the view
    private final CreateAccountPresenter view;
    // Firebase authentication instance for handling user accounts
    private final FirebaseAuth mAuth;

    // Constructor to initialize the view and FirebaseAuth instance
    public CreateAccountModelImpl(CreateAccountPresenter view) {
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
                        // Get the newly created user's ID
                        String userId = mAuth.getCurrentUser().getUid();

                        // Instantiate a User object
                        User newUser = new User(fullName, userId, password, email);

                        // Push the user object to the Firebase Realtime Database
                        FirebaseDatabase.getInstance().getReference("users")
                                .child(userId)
                                .setValue(newUser)
                                .addOnCompleteListener(dbTask -> {
                                    if (dbTask.isSuccessful()) {
                                        view.showSuccess("Account created successfully and saved to database!");
                                        view.navigateToWelcomeScreen();
                                    } else {
                                        view.showError("Error saving to database: " + dbTask.getException().getMessage());
                                    }
                                });
                    } else {
                        view.showError("Error: " + task.getException().getMessage());
                    }
                });
    }
}
