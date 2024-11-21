package com.example.b07demosummer2024;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class EcoGaugeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eco_gauge); // Main layout
        setTitle("Eco Gauge");

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new ChartPagerAdapter(this));
    }
}
