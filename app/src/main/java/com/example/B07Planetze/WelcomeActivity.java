package com.example.B07Planetze;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.B07Planetze.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // TextView references for welcome message and introduction
        TextView welcomeMessage = findViewById(R.id.welcome_message);
        TextView introductionMessage = findViewById(R.id.introduction_message);

        // Set messages
        welcomeMessage.setText("Welcome to the Planetze app!");
        introductionMessage.setText("For the initial setup we will first ask a few questions to calculate your current annual carbon footprint based on your lifestyle. You only need to do this once unless you choose to recalculate later.");

        // Continue button
        Button continueButton = findViewById(R.id.continue_button);
        continueButton.setOnClickListener(v -> {
            // Navigate to the next activity
            Intent intent = new Intent(WelcomeActivity.this, AnnualCarbonFootprintCountrySelectionActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
