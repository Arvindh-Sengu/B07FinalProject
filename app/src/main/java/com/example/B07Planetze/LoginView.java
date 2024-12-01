package com.example.B07Planetze;

public interface LoginView {
    void showError(String message);
    void showSuccess(String message);
    void navigateToHomeScreen();
    void navigateToCreateAccountScreen();
    void navigateToPasswordResetScreen();
}

