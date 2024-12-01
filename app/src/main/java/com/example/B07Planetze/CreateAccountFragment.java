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

public class CreateAccountFragment extends Fragment implements CreateAccountView {

    private CreateAccountPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the activity_create_account.xml layout
        View view = inflater.inflate(R.layout.activity_create_account, container, false);

        // Initialize Presenter
        presenter = new CreateAccountPresenterImpl(this);

        // UI Setup
        EditText nameEditText = view.findViewById(R.id.nameEnterEditText);
        EditText emailEditText = view.findViewById(R.id.emailEditText);
        EditText passwordEditText = view.findViewById(R.id.passwordEditText);
        EditText confirmPasswordEditText = view.findViewById(R.id.confirmPasswordEditText);
        Button createAccountButton = view.findViewById(R.id.createAccountButton);
        TextView openLogin = view.findViewById(R.id.openLogin);

        // Handle Create Account Button
        createAccountButton.setOnClickListener(v -> {
            String fullName = nameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String confirmPassword = confirmPasswordEditText.getText().toString().trim();

            if (validateInputs(fullName, email, password, confirmPassword)) {
                presenter.createAccount(email, password, confirmPassword, fullName);
            }
        });

        // Handle Navigation to Login Screen
        openLogin.setOnClickListener(v -> navigateToLoginScreen());

        return view;
    }

    //boolean method that checks if the account inputs works and if so returns true if not returns false and an error
    private boolean validateInputs(String fullName, String email, String password, String confirmPassword) {
        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showError("Please fill all fields");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            showError("Passwords do not match");
            return false;
        }
        return true;
    }

    // sends error message via android messages
    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    // sends message on success
    @Override
    public void showSuccess(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    // changes screen to home screen
    @Override
    public void navigateToHomeScreen() {
        Intent intent = new Intent(getActivity(), HomeScreenActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }

    // navigates user to the login screen
    @Override
    public void navigateToLoginScreen() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        }

}
