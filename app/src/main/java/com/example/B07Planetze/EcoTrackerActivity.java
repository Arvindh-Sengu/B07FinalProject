package com.example.B07Planetze;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.B07Planetze.R;

public class EcoTrackerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_tracker);
        setTitle("Eco Tracker");
    }
}