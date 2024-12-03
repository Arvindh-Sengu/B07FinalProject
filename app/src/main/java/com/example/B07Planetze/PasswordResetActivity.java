package com.example.B07Planetze;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

// Really just UI set up honestly
public class PasswordResetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content, new PasswordResetFragment())
                    .commit();
        }
    }
}

