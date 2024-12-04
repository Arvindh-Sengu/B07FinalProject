package com.example.b07demosummer2024;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class EcoGaugeActivity extends AppCompatActivity {

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

        TextView timePeriodTextView = findViewById(R.id.timePeriodTextView);


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
                LinearLayout dotsContainer = findViewById(R.id.viewPagerIndicatorIconsLayout);
                switch (position) {
                    case 0: // daily case:
                        dotsContainer.setVisibility(View.GONE);
                        timePeriodTextView.setText("Your emissions Today");

                        infoCardFragment = InfoCardFragment.newInstance(DataProcessor.getDailyCategoricalData(emissionsData, currentDate, EcoGaugeActivity.this));
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.infoCardContainer, infoCardFragment)
                                .commit();
                        break;

                    case 1: //weekly
                        dotsContainer.setVisibility(View.VISIBLE);
                        timePeriodTextView.setText("Your emissions this Week");

                        infoCardFragment = InfoCardFragment.newInstance(DataProcessor.getWeeklyCategoricalData(emissionsData, currentDate, EcoGaugeActivity.this));
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.infoCardContainer, infoCardFragment)
                                .commit();
                        break;

                    case 2: //monthly
                        dotsContainer.setVisibility(View.VISIBLE);
                        timePeriodTextView.setText("Your emissions this Month");

                        infoCardFragment = InfoCardFragment.newInstance(DataProcessor.getMonthlyCategoricalData(emissionsData, currentDate, EcoGaugeActivity.this));
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.infoCardContainer, infoCardFragment)
                                .commit();

                        break;

                    case 3: //yearly
                        dotsContainer.setVisibility(View.VISIBLE);
                        timePeriodTextView.setText("Your emissions this Year");

                        infoCardFragment = InfoCardFragment.newInstance(DataProcessor.getYearlyCategoricalData(emissionsData, currentDate, EcoGaugeActivity.this));
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.infoCardContainer, infoCardFragment)
                                .commit();
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action needed
            }
        });



        //infocard frag:
        // Initialize InfoCardFragment
        infoCardFragment = InfoCardFragment.newInstance(DataProcessor.getDailyCategoricalData(emissionsData, currentDate, this));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.infoCardContainer, infoCardFragment)
                .commit();


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
