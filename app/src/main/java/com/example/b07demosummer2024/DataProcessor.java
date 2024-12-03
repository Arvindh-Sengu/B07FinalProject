package com.example.b07demosummer2024;

import android.content.Context;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class DataProcessor {



    /**
     * Processes the pie chart / categorical data based on the time period.
     * @param emissionsData The emissions data: {LocalDate: {Category: emission value}}
     * @param timePeriod The time period: 0 (Weekly), 1 (Monthly), 2 (Yearly).
     * @param selectedDate The reference date (e.g., today).
     * @return An ArrayList<Float> of emissions totals by category for the selected period.
     */
    public static HashMap<String, Float> getCategoricalData(
            HashMap<LocalDate, HashMap<String, Float>> emissionsData,
            int timePeriod,
            LocalDate selectedDate,
            Context context) {

        // Initialize categoryTotals with all categories from the string array and default values of 0
        HashMap<String, Float> categoryTotals = new HashMap<>();
        String[] categories = context.getResources().getStringArray(R.array.Categories);
        for (String category : categories) {
            categoryTotals.put(category, 0f);
        }

        switch (timePeriod) {
            case 0: // Daily: Consider the categories from a single day
                HashMap<String, Float> dayData = emissionsData.get(selectedDate);
                if (dayData != null) {
                    for (String category : categoryTotals.keySet()) {
                        Float value = dayData.getOrDefault(category, 0f);
                        categoryTotals.put(category, categoryTotals.get(category) + value);
                    }
                }
                break;

            case 1: // Weekly: Consider the categories from Sunday to today (weekly)
                LocalDate startOfWeek = selectedDate.minusDays(selectedDate.getDayOfWeek().getValue() % 7); // Last Sunday
                for (int i = 0; i <= selectedDate.getDayOfWeek().getValue(); i++) {
                    LocalDate currentDay = startOfWeek.plusDays(i);
                    HashMap<String, Float> weeklyData = emissionsData.get(currentDay);
                    if (weeklyData != null) {
                        for (String category : categoryTotals.keySet()) {
                            Float value = weeklyData.getOrDefault(category, 0f);
                            categoryTotals.put(category, categoryTotals.get(category) + value);
                        }
                    }
                }
                break;

            case 2: // Monthly: Consider the categories from 1st of the month to today (monthly)
                LocalDate startOfMonth = selectedDate.withDayOfMonth(1);
                int daysInMonth = selectedDate.getDayOfMonth();
                for (int i = 0; i < daysInMonth; i++) {
                    LocalDate currentDay = startOfMonth.plusDays(i);
                    HashMap<String, Float> monthlyData = emissionsData.get(currentDay);
                    if (monthlyData != null) {
                        for (String category : categoryTotals.keySet()) {
                            Float value = monthlyData.getOrDefault(category, 0f);
                            categoryTotals.put(category, categoryTotals.get(category) + value);
                        }
                    }
                }
                break;

            case 3: // Yearly: Consider the categories from the start of the year to today (yearly)
                for (LocalDate date : emissionsData.keySet()) {
                    if (date.getYear() == selectedDate.getYear()) {
                        HashMap<String, Float> yearlyData = emissionsData.get(date);
                        if (yearlyData != null) {
                            for (String category : categoryTotals.keySet()) {
                                Float value = yearlyData.getOrDefault(category, 0f);
                                categoryTotals.put(category, categoryTotals.get(category) + value);
                            }
                        }
                    }
                }
                break;

            default:
                throw new IllegalArgumentException("Invalid time period selection");
        }

        return categoryTotals;
    }






    /**
     * Processes bar chart data based on the time period.
     * @param emissionsData The emissions data: {LocalDate: {Category: emission value}}
     * @param timePeriod The time period: 0 (Weekly), 1 (Monthly), 2 (Yearly).
     * @param selectedDate The reference date (e.g., today).
     * @return An ArrayList<Float> of emissions totals for the period.
     */
    public static ArrayList<Float> getPeriodicalChartData(
            HashMap<LocalDate, HashMap<String, Float>> emissionsData,
            int timePeriod,
            LocalDate selectedDate) {

        ArrayList<Float> barChartData = new ArrayList<>();

        switch (timePeriod) {
            case 1: // Weekly: From Sunday to today
                LocalDate startOfWeek = selectedDate.minusDays(selectedDate.getDayOfWeek().getValue() % 7); // Last Sunday
                int daysToInclude = selectedDate.getDayOfWeek().getValue(); // Number of days from Sunday to today
                for (int i = 0; i <= daysToInclude; i++) { // Iterate up to the current day
                    LocalDate currentDay = startOfWeek.plusDays(i);
                    float dailyTotal = 0f;

                    // Add emissions if data exists; otherwise, default to 0
                    HashMap<String, Float> dayData = emissionsData.get(currentDay);
                    if (dayData != null) {
                        dailyTotal = dayData.values().stream().reduce(0f, Float::sum);
                    }

                    barChartData.add(dailyTotal);
                }
                break;

            case 2: // Monthly: From the 1st of the month to today
                LocalDate startOfMonth = selectedDate.withDayOfMonth(1);
                int daysInMonth = selectedDate.getDayOfMonth(); // Only consider days up to today
                for (int i = 0; i < daysInMonth; i++) {
                    LocalDate currentDay = startOfMonth.plusDays(i);
                    float dailyTotal = 0f;

                    // Add emissions if data exists; otherwise, default to 0
                    HashMap<String, Float> dayData = emissionsData.get(currentDay);
                    if (dayData != null) {
                        dailyTotal = dayData.values().stream().reduce(0f, Float::sum);
                    }

                    barChartData.add(dailyTotal);
                }
                break;

            case 3: // Yearly: From January to the current month
                for (int month = 1; month <= selectedDate.getMonthValue(); month++) {
                    float monthlyTotal = 0f;

                    // Filter relevant dates directly for the current month
                    for (Map.Entry<LocalDate, HashMap<String, Float>> entry : emissionsData.entrySet()) {
                        LocalDate date = entry.getKey();
                        if (date.getYear() == selectedDate.getYear() && date.getMonthValue() == month) {
                            HashMap<String, Float> dayData = entry.getValue();
                            if (dayData != null) {
                                monthlyTotal += dayData.values().stream().reduce(0f, Float::sum);
                            }
                        }
                    }

                    barChartData.add(monthlyTotal);
                }
                break;

            default:
                throw new IllegalArgumentException("Invalid time period selection");
        }

        return barChartData;
    }


    public static ArrayList<Float> getLineChartYearlyData(
            HashMap<LocalDate, HashMap<String, Float>> emissionsData,
            LocalDate selectedDate) {

        ArrayList<Float> lineChartData = new ArrayList<>();

        // Start from January 1st of the current year
        LocalDate startOfYear = selectedDate.withDayOfYear(1);
        int daysInYear = selectedDate.getDayOfYear(); // Total days to include, from Jan 1 to selected date

        for (int i = 0; i < daysInYear; i++) {
            LocalDate currentDay = startOfYear.plusDays(i);
            float dailyTotal = 0f;

            // Get the emissions data for the current day, or default to 0 if null
            HashMap<String, Float> dayData = emissionsData.get(currentDay);
            if (dayData != null) {
                dailyTotal = dayData.values().stream().reduce(0f, Float::sum);
            }

            lineChartData.add(dailyTotal);
        }

        return lineChartData;
    }


}

