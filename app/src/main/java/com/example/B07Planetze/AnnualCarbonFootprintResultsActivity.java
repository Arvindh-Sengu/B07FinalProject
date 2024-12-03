package com.example.B07Planetze;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import androidx.appcompat.app.AppCompatActivity;

import com.B07Planetze.R;

import java.util.ArrayList;
import java.util.List;

public class AnnualCarbonFootprintResultsActivity extends AppCompatActivity {

    private static final double KG_TO_TONS_OF_CO2 = 1000.0;
    private static final double GLOBAL_TARGET_CO2_TONS = 2.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.annual_carbon_footprint_results);

        // Get the emissions data from the intent and round them to 2 decimal places
        double transportationEmissionsInTons = Math.round((getIntent().getDoubleExtra("TRANSPORTATION_EMISSIONS", 0) / KG_TO_TONS_OF_CO2) * 100.0) / 100.0;
        double foodEmissionsInTons = Math.round((getIntent().getDoubleExtra("FOOD_EMISSIONS", 0) / KG_TO_TONS_OF_CO2) * 100.0) / 100.0;
        double housingEmissionsInTons = Math.round((getIntent().getDoubleExtra("HOUSING_EMISSIONS", 0) / KG_TO_TONS_OF_CO2) * 100.0) / 100.0;
        double consumptionEmissionsInTons = Math.round((getIntent().getDoubleExtra("CONSUMPTION_EMISSIONS", 0) / KG_TO_TONS_OF_CO2) * 100.0) / 100.0;
        String selectedCountry = getIntent().getStringExtra("SELECTED_COUNTRY");
        double countryEmissionInTons = Math.round(getIntent().getDoubleExtra("COUNTRY_EMISSION", 0) * 100.0) / 100.0;

        // Calculate total emissions and round
        double totalEmissionsInTons = Math.round((transportationEmissionsInTons + foodEmissionsInTons + housingEmissionsInTons + consumptionEmissionsInTons) * 100.0) / 100.0;

        // TextView references
        TextView overviewText = findViewById(R.id.overview_text);
        TextView breakdownText = findViewById(R.id.breakdown_text);
        TextView comparisonText = findViewById(R.id.comparison_text);
        TextView globalComparisonText = findViewById(R.id.global_comparison_text);

        // Set overview text
        String totalAnnualCarbonFootprint = "Your total annual carbon footprint is " + totalEmissionsInTons + " tons of CO2e per year.";
        overviewText.setText(totalAnnualCarbonFootprint);

        String breakDownByCategory = "Breakdown by category:\n" +
                "- Transportation: " + transportationEmissionsInTons + " tons\n" +
                "- Food: " + foodEmissionsInTons + " tons\n" +
                "- Housing: " + housingEmissionsInTons + " tons\n" +
                "- Consumption: " + consumptionEmissionsInTons + " tons";

        // Set breakdown text
        breakdownText.setText(breakDownByCategory);

        // Calculate difference from national average and round
        double differenceInTons = totalEmissionsInTons - countryEmissionInTons;
        double differencePercentage = Math.round(((differenceInTons / countryEmissionInTons) * 100.0) * 100.0) / 100.0;

        String comparisonMessage;
        if (differencePercentage < 0) {
            comparisonMessage = "Your carbon footprint is " + Math.abs(differencePercentage) + "% below the national average for " + selectedCountry + ".";
        } else {
            comparisonMessage = "Your carbon footprint is " + Math.abs(differencePercentage) + "% above the national average for " + selectedCountry + ".";
        }

        // Set comparison text
        comparisonText.setText(comparisonMessage);

        // Global target comparison
        String globalComparisonMessage;
        if (totalEmissionsInTons <= GLOBAL_TARGET_CO2_TONS) {
            globalComparisonMessage = "Your carbon footprint meets the global target of " + GLOBAL_TARGET_CO2_TONS + " tons per year.";
        } else {
            double excessEmissions = Math.round((totalEmissionsInTons - GLOBAL_TARGET_CO2_TONS) * 100.0) / 100.0;
            globalComparisonMessage = "Your carbon footprint exceeds the global target by " + excessEmissions + " tons per year.";
        }

        // Set global comparison text
        globalComparisonText.setText(globalComparisonMessage);


        // Setup pie chart
        PieChart pieChart = findViewById(R.id.pie_chart);
        setupPieChart(pieChart, (float) transportationEmissionsInTons,(float) foodEmissionsInTons, (float) housingEmissionsInTons, (float) consumptionEmissionsInTons);

        // Setup home button
        Button homeButton = findViewById(R.id.home_button);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(AnnualCarbonFootprintResultsActivity.this, HomeScreenActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void setupPieChart(PieChart pieChart, float transportationEmissions, float foodEmissions, float housingEmissions, float consumptionEmissions) {

        // Create entries for the PieChart
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(transportationEmissions, "Transportation"));
        entries.add(new PieEntry(foodEmissions, "Food"));
        entries.add(new PieEntry(housingEmissions, "Housing"));
        entries.add(new PieEntry(consumptionEmissions, "Consumption"));

        // Create dataset
        PieDataSet dataSet = new PieDataSet(entries, "Carbon Footprint Breakdown");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS); // Use built-in color template
        dataSet.setValueTextSize(12f); // Set text size for values
        dataSet.setValueTextColor(android.graphics.Color.BLACK); // Set text color for values

        // Create PieData object
        PieData pieData = new PieData(dataSet);

        // Configure the PieChart
        pieChart.setData(pieData);
        pieChart.setUsePercentValues(true); // Show values as percentages
        pieChart.getDescription().setEnabled(false); // Disable description text
        pieChart.setDrawHoleEnabled(true); // Enable center hole
        pieChart.setHoleRadius(40f); // Adjust hole radius
        pieChart.setTransparentCircleRadius(45f); // Adjust transparent circle radius
        pieChart.setCenterText("Carbon Footprint"); // Set center text
        pieChart.setCenterTextSize(16f); // Adjust center text size
        pieChart.setCenterTextColor(android.graphics.Color.DKGRAY); // Adjust center text color
        pieChart.getLegend().setEnabled(true); // Enable legend
        pieChart.getLegend().setTextColor(android.graphics.Color.DKGRAY); // Set legend text color
        pieChart.getLegend().setTextSize(12f); // Set legend text size

        // Refresh the chart
        pieChart.invalidate(); // Refresh the chart with updated data
    }
}
