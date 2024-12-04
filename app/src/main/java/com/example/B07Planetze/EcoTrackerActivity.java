package com.example.B07Planetze;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.B07Planetze.R;
import com.google.firebase.auth.FirebaseAuthException;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EcoTrackerActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_tracker);
        setTitle("Eco Tracker");
    }

    public void onBackClicked(View view) {
        setContentView(R.layout.new_tracker);
    }

    public void addEntryClicked(View view) {
        setContentView(R.layout.daily_questions);
        System.out.println("entry");
    }

    public void onBackClicked2(View view) {
        finish();

    }

    public void onSubmitClicked(View view) {
        
    }
}