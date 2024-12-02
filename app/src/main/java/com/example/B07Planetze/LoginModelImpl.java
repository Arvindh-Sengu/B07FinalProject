package com.example.B07Planetze;

import com.google.firebase.auth.FirebaseAuth;

public class LoginModelImpl implements LoginModel {

    // Reference to the view
    private final LoginPresenter view;
    // Firebase authentication instance for handling user accounts
    private final FirebaseAuth mAuth;

    // Constructor to initialize the view and FirebaseAuth instance
    public LoginModelImpl(LoginPresenter view) {
        this.view = view;
        this.mAuth = FirebaseAuth.getInstance();
    }

    // uses firebase to check if a given email and password is attatched to an account
    // if so it logs in the user
    @Override
    public void login(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            view.showError("Please fill all fields");
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        view.showSuccess("Sign-in successful!");
                        view.navigateToHomeScreen();
                    } else {
                        view.showError("Error: " + task.getException().getMessage());
                    }
                });
    }
}
