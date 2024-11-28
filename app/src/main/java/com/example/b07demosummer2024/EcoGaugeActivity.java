package com.example.b07demosummer2024;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;

public class EcoGaugeActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ChartPagerAdapter chartPagerAdapter;
    private int currentPosition = 0;

    private TabLayout tabLayout;

    private TabLayoutMediator mediator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eco_gauge);

        // Set up the spinner
        Spinner timePeriodSpinner = findViewById(R.id.time_period_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.time_periods, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timePeriodSpinner.setAdapter(adapter);

        // Set up ViewPager2 for swiping between charts
        viewPager = findViewById(R.id.viewPager);
        chartPagerAdapter = new ChartPagerAdapter(this, getDummyDataForCategories(0), getDummyDataForTimePeriod(0), 0);
        viewPager.setAdapter(chartPagerAdapter);
        //Set up TabLayout for tab indicator
        tabLayout = findViewById(R.id.tabLayout);

        // Link TabLayout with ViewPager2
        mediator = new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
        });

        if (!mediator.isAttached()){
            mediator.attach();
        }

        // Update the ViewPager2 adapter when a spinner item is selected
        timePeriodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                ArrayList<Float> newCategoricalData = getDummyDataForCategories(position);
                ArrayList<Float> newPeriodicalData = getDummyDataForTimePeriod(position);
                chartPagerAdapter = new ChartPagerAdapter(EcoGaugeActivity.this, newCategoricalData, newPeriodicalData, position);
                viewPager.setAdapter(chartPagerAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action needed
            }
        });
    }

    // Simulated data for different time periods
    private ArrayList<Float> getDummyDataForCategories(int timePeriod) {
        switch (timePeriod) {
            case 0: // Daily
                return new ArrayList<>(Arrays.asList(1.5f, 2.0f, 2.5f));
            case 1: // Weekly
                return new ArrayList<>(Arrays.asList(10f, 15f, 20f));
            case 2: // Monthly
                return new ArrayList<>(Arrays.asList(40f, 20f, 60f));
            case 3: // Yearly
                return new ArrayList<>(Arrays.asList(400f, 500f, 600f));
            default:
                return new ArrayList<>();
        }
    }

    private ArrayList<Float> getDummyDataForTimePeriod(int timePeriod) {
        switch (timePeriod) {
            case 0: // Daily
                return new ArrayList<>(Arrays.asList(5f, 4f, 25f));
            case 1: // Weekly
                return new ArrayList<>(Arrays.asList(30f, 1f, 15f));
            case 2: // Monthly
                return new ArrayList<>(Arrays.asList(20f, 20f, 30f));
            case 3: // Yearly
                return new ArrayList<>(Arrays.asList(371f, 500f, 681f));
            default:
                return new ArrayList<>();
        }
    }




}
