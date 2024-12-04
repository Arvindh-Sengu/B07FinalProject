package com.example.B07Planetze;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.B07Planetze.R;
import android.view.View;

public class EcoTrackerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_tracker);
        setTitle("Eco Tracker");
    }

    public void onBackClicked(View view) {
        setContentView(R.layout.new_tracker);
        System.out.println("hi");
    }

    public void addEntryClicked(View view) {
        setContentView(R.layout.daily_questions);
        System.out.println("entry");
    }

    public void onSubmitlicked(View view) {

    }
}