package com.example.B07Planetze;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

// Really just UI set up honestly
public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the CreateAccountFragment
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content, new CreateAccountFragment()) // Create an instance of the fragment
                    .commit();
        }
    }
}

