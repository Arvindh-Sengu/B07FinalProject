package com.example.B07Planetze;

// List of needed methods for CreateAccounts view
public interface CreateAccountView {
    void showError(String message);
    void showSuccess(String message);
    void navigateToHomeScreen();
    void navigateToLoginScreen();
}
