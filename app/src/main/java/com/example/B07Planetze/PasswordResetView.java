package com.example.B07Planetze;

public interface PasswordResetView {
    void showError(String message);
    void showSuccess(String message);
    void navigateToLoginScreen();
}
