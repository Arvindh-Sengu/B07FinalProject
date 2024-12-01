package com.example.B07Planetze;

import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;

public class HousingCarbonFootprintCalculator {
    HashMap<String, Double> emissionsMap = new HashMap<>();

    // Constructor to load CSV data from res/raw
    public HousingCarbonFootprintCalculator(Context context, int rawResourceId) throws IOException {
        loadCsv(context, rawResourceId);
    }

    private void loadCsv(Context context, int rawResourceId) throws IOException {
        InputStream inputStream = context.getResources().openRawResource(rawResourceId);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length < 6) { // Ensure at least 6 columns
                    continue; // Skip this row
                }

                // Combine relevant columns into a key
                String key = String.join(",", values[0], values[1], values[2], values[3], values[4]);
                double co2Emission = Double.parseDouble(values[5]);
                emissionsMap.put(key, co2Emission);
            }
        }
    }

    public int calculateHousingEmisssions(String homeType, String occupants, String homeSize, String homeHeatingSource,
                                          String electricityBill, String waterHeatingSource, String renewableEnergyUsage) {
        // Create key for emissions lookup
        String key = String.join(",", homeType, homeSize, occupants, homeHeatingSource, electricityBill);
        double co2Emission = emissionsMap.getOrDefault(key, 0.0);

        // Add 233 kg if the water heating source differs from the home heating source
        if (!homeHeatingSource.equalsIgnoreCase(waterHeatingSource)) {
            if (waterHeatingSource.equalsIgnoreCase("Electricity") || waterHeatingSource.equalsIgnoreCase("Solar")) {
                co2Emission -= 233; // Reduce by 233 if water heating source is Electricity or Solar
            } else {
                co2Emission += 233; // Add 233 otherwise
            }
        }

        // Adjust for renewable energy usage
        if (renewableEnergyUsage.equalsIgnoreCase("Yes, primarily (more than 50% of energy use)")) {
            co2Emission -= 6000;
        } else if (renewableEnergyUsage.equalsIgnoreCase("Yes, partially (less than 50% of energy use)")) {
            co2Emission -= 4000;
        }

        // Ensure emissions are not negative
        return (int) Math.max(co2Emission, 0);
    }
}
