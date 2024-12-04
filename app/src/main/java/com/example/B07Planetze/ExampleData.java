package com.example.B07Planetze;


import java.time.LocalDate;
import java.util.HashMap;

public class ExampleData {
    private static HashMap<LocalDate, HashMap<String, Float>> emissionsData = new HashMap<>();

    static {
        // Start from a specific date
        LocalDate startDate = LocalDate.of(2024, 12, 1);

        for (int i = 0; i < 21; i++) { // Add 21 days of data
            LocalDate date = startDate.plusDays(i);

            HashMap<String, Float> dailyData = new HashMap<>();
            //bro named the categories "category 1, 2, 3" and was wondering
            // why pie chart didnt store it when he explicitly made pie chart to not store categories with the correct name...
            dailyData.put("Garbage", 20.0f + (i * 12));
            dailyData.put("Transportation", 50.0f - (i * 5));
            dailyData.put("Shopping", 25.0f + (i % 3));
            emissionsData.put(date, dailyData);
        }
    }

    public static HashMap<LocalDate, HashMap<String, Float>> getEmissionsData() {
        return emissionsData;
    }

}
