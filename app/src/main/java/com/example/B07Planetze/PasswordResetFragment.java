package com.example.B07Planetze;

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

public class PasswordResetFragment extends Fragment implements PasswordResetView {

    private PasswordResetPresenter presenter;
    private EditText emailEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_password_reset, container, false);

        // Initialize Presenter
        presenter = new PasswordResetPresenterImpl(this);

        // UI Setup
        emailEditText = view.findViewById(R.id.emailEditText);
        Button sendEmailButton = view.findViewById(R.id.sendEmailButton);
        TextView openLogin = view.findViewById(R.id.openLogin);

        // Handle Send Email Button
        sendEmailButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            presenter.handlePasswordReset(email);
        });

        // Handle Navigation to Login
        openLogin.setOnClickListener(v -> navigateToLoginScreen());

        return view;
    }

    // Error message
    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    // Success message
    @Override
    public void showSuccess(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        emailEditText.setText(""); // Clear the input field after success
    }

    // navigates back to the login screen
    @Override
    public void navigateToLoginScreen() {
        if (getActivity() != null) {
            getActivity().finish(); // Close the activity and return to Login
        }
    }
}
