package com.example.b07demosummer2024;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class EcoGaugeActivity extends AppCompatActivity implements BarChartFragment.DataListener {

    private InfoCardFragment infoCardFragment;
    private ViewPager2 viewPager;
    private ChartPagerAdapter chartPagerAdapter;
    private int currentPosition = 0;

    private TabLayout tabLayout;

    private TabLayoutMediator mediator;

    private LocalDate currentDate = LocalDate.of(2024, 12, 15);
    //bro set the current date to the 2nd date and was wondering why only 2 data points showed....



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //temporary data
        HashMap<LocalDate, HashMap<String, Float>> emissionsData = ExampleData.getEmissionsData();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eco_gauge);

        // Set up the spinner
        Spinner timePeriodSpinner = findViewById(R.id.time_period_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.time_periods, R.layout.spinner_item_dropdown);
        adapter.setDropDownViewResource(R.layout.spinner_item_dropdown);
        timePeriodSpinner.setAdapter(adapter);

        // Set up ViewPager2 for swiping between charts
        viewPager = findViewById(R.id.viewPager);
        chartPagerAdapter = new ChartPagerAdapter(this, ExampleData.getEmissionsData(), 0, currentDate);
        viewPager.setAdapter(chartPagerAdapter);

        // dot indicators
        viewPager.registerOnPageChangeCallback(new ViewPagerListener(new ViewPagerListener.OnPageSelectedListener() {
            @Override
            public void onPageSelected(int position) {
                updateDots(position);
            }
        }));

        //zoom transform
        viewPager.setPageTransformer(new ViewPager2.PageTransformer() {
            private static final float MIN_SCALE = 0.65f;
            private static final float MIN_ALPHA = 0.3f;

            @Override
            public void transformPage(View page, float position) {
                if (position < -1) {
                    page.setAlpha(0f); // Page is way off-screen to the left
                } else if (position <= 1) { // [-1,1]
                    // Scale the page down (between MIN_SCALE and 1)
                    float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                    page.setScaleX(scaleFactor);
                    page.setScaleY(scaleFactor);

                    // Fade the page relative to its size
                    page.setAlpha(Math.max(MIN_ALPHA, 1 - Math.abs(position)));
                } else {
                    page.setAlpha(0f); // Page is way off-screen to the right
                }
            }
        });


        // Update the ViewPager2 adapter when a spinner item is selected
        timePeriodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                chartPagerAdapter = new ChartPagerAdapter(EcoGaugeActivity.this, emissionsData, position, currentDate);
                viewPager.setAdapter(chartPagerAdapter);
                chartPagerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action needed
            }
        });



        //infocard frag:
        // Initialize InfoCardFragment
        infoCardFragment = InfoCardFragment.newInstance(new HashMap<>());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.infoCardContainer, infoCardFragment)
                .commit();


    }

    @Override
    public void onDataSelected(HashMap<String, Float> emissionData) {
        if (infoCardFragment != null && emissionData != null) {
            infoCardFragment.populateTable(emissionData);
        }
    }


    private void updateDots(int position) {
        ImageView firstDot = findViewById(R.id.firstDotImageView);
        ImageView secondDot = findViewById(R.id.secondDotImageView);
        ImageView thirdDot = findViewById(R.id.thirdDotImageView);
        switch (position) {
            case 0:
                firstDot.setImageResource(R.drawable.current_position_icon);
                secondDot.setImageResource(R.drawable.disable_position_icon);
                thirdDot.setImageResource(R.drawable.disable_position_icon);
                break;
            case 1:
                firstDot.setImageResource(R.drawable.disable_position_icon);
                secondDot.setImageResource(R.drawable.current_position_icon);
                thirdDot.setImageResource(R.drawable.disable_position_icon);
                break;
            case 2:
                firstDot.setImageResource(R.drawable.disable_position_icon);
                secondDot.setImageResource(R.drawable.disable_position_icon);
                thirdDot.setImageResource(R.drawable.current_position_icon);
                break;
        }
    }

}
