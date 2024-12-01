package com.example.B07Planetze;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import com.B07Planetze.R;

public class AnnualCarbonFootprintCountrySelectionActivity extends AppCompatActivity {

    private Spinner countrySelectionSpinner;
    private Button nextButton;
    private HashMap<String, Double> countryEmissionsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.annual_carbon_footprint_country_selector);

        // Initialize UI elements
        countrySelectionSpinner = findViewById(R.id.countrySpinner);
        nextButton = findViewById(R.id.nextButton);

        // Initialize data structure
        countryEmissionsData = new HashMap<>();

        // Load country emissions data from CSV
        loadCountryEmissionsDataFromCSV();

        // Button click listener to get emission data for selected country
        nextButton.setOnClickListener(v -> {
            String selectedCountry = countrySelectionSpinner.getSelectedItem().toString();
            Double emissionForCountry = countryEmissionsData.get(selectedCountry);

            // Create an intent to navigate to the next activity
            Intent intent = new Intent(AnnualCarbonFootprintCountrySelectionActivity.this, AnnualCarbonFootprintQuestionsActivity.class);

            //pass the selected country and emission factor to the next activity
            intent.putExtra("SELECTED_COUNTRY", selectedCountry);
            intent.putExtra("COUNTRY_EMISSION", emissionForCountry);

            // Start the new activity
            startActivity(intent);

        });
    }

    private void loadCountryEmissionsDataFromCSV() {
        // Access the data and create a reader for it
        InputStream inputStream = getResources().openRawResource(R.raw.global_averages);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        ArrayList<String> countryList = new ArrayList<>();

        try {

            // Skip header
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] countryData = line.split(",");
                String country = countryData[0].trim();
                double emissionFactor = Double.parseDouble(countryData[1].trim());

                // Store the country and emission factor in the HashMap
                countryEmissionsData.put(country, emissionFactor);

                // Add the country to the list for Spinner
                countryList.add(country);


            }
            reader.close();
        } catch (IOException e) {
            Log.e("CSV", "Error reading CSV file from raw", e);
        }

        //Add countries to spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySelectionSpinner.setAdapter(adapter);
    }
}
