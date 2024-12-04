package com.example.B07Planetze;
// Interface for the CreateAccountModel
// to validate and register a new user into the system.
public interface CreateAccountModel {
    void createAccount(String email, String password, String confirmPassword, String fullName);
}
