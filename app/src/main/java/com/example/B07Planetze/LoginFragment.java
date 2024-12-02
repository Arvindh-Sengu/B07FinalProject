package com.example.B07Planetze;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.B07Planetze.R;
// as this is responsible for connecting the front end to the back end logic as well as
// basic front end methods and functions this would be the presenter
public class LoginFragment extends Fragment implements LoginView {

    // UI set up
    private LoginModel presenter;
    private EditText emailEditText;
    private EditText passwordEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);

        // Initialize Presenter
        presenter = new LoginModelImpl(this);

        // UI Setup
        emailEditText = view.findViewById(R.id.emailEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        Button loginButton = view.findViewById(R.id.loginButton);
        TextView openCreateAccount = view.findViewById(R.id.openCreateAccount);
        TextView forgotPassword = view.findViewById(R.id.openPasswordReset);

        // Handle Login Button
        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            presenter.login(email, password);
        });

        // Handle Navigation to Create Account Screen
        openCreateAccount.setOnClickListener(v -> navigateToCreateAccountScreen());

        // Handle Navigation to Password Reset Screen
        forgotPassword.setOnClickListener(v -> navigateToPasswordResetScreen());

        return view;
    }

    // Error message
    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    // Successful login
    @Override
    public void showSuccess(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    //Change view to homescreen used after login succsess
    @Override
    public void navigateToHomeScreen() {
        Intent intent = new Intent(getActivity(), HomeScreenActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }
    // Used to navigate to the create account screen
    @Override
    public void navigateToCreateAccountScreen() {
        Intent intent = new Intent(getActivity(), CreateAccountActivity.class);
        startActivity(intent);
    }
    // used to navigate to the password reset screen
    @Override
    public void navigateToPasswordResetScreen() {
        Intent intent = new Intent(getActivity(), PasswordResetActivity.class);
        startActivity(intent);
    }
}

