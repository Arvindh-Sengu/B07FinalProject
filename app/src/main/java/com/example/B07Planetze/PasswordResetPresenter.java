package com.example.B07Planetze;

public interface PasswordResetPresenter {
    void showError(String message);
    void showSuccess(String message);
    void navigateToLoginScreen();
}
