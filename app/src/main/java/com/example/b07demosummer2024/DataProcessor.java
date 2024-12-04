package com.example.b07demosummer2024;

import android.content.Context;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataProcessor {

    /**
     * Processes the categorical data for the daily time period (0).
     * @param emissionsData The emissions data: {LocalDate: {Category: emission value}}
     * @param selectedDate The reference date (e.g., today).
     * @param context The context for accessing resources.
     * @return A HashMap of category totals.
     */
    public static HashMap<String, Float> getDailyCategoricalData(
            HashMap<LocalDate, HashMap<String, Float>> emissionsData,
            LocalDate selectedDate,
            Context context) {

        HashMap<String, Float> categoryTotals = initializeCategoryTotals(context);
        HashMap<String, Float> dayData = emissionsData.get(selectedDate);

        if (dayData != null) {
            for (String category : categoryTotals.keySet()) {
                Float value = dayData.getOrDefault(category, 0f);
                categoryTotals.put(category, categoryTotals.get(category) + value);
            }
        }

        return categoryTotals;
    }

    /**
     * Processes the categorical data for the weekly time period (1).
     * @param emissionsData The emissions data: {LocalDate: {Category: emission value}}
     * @param selectedDate The reference date (e.g., today).
     * @param context The context for accessing resources.
     * @return A HashMap of category totals.
     */
    public static HashMap<String, Float> getWeeklyCategoricalData(
            HashMap<LocalDate, HashMap<String, Float>> emissionsData,
            LocalDate selectedDate,
            Context context) {

        HashMap<String, Float> categoryTotals = initializeCategoryTotals(context);
        LocalDate startOfWeek = selectedDate.minusDays(selectedDate.getDayOfWeek().getValue() % 7); // Last Sunday

        for (int i = 0; i < selectedDate.getDayOfWeek().getValue(); i++) {
            LocalDate currentDay = startOfWeek.plusDays(i);
            HashMap<String, Float> weeklyData = emissionsData.get(currentDay);
            if (weeklyData != null) {
                for (String category : categoryTotals.keySet()) {
                    Float value = weeklyData.getOrDefault(category, 0f);
                    categoryTotals.put(category, categoryTotals.get(category) + value);
                }
            }
        }

        return categoryTotals;
    }

    /**
     * Processes the categorical data for the monthly time period (2).
     * @param emissionsData The emissions data: {LocalDate: {Category: emission value}}
     * @param selectedDate The reference date (e.g., today).
     * @param context The context for accessing resources.
     * @return A HashMap of category totals.
     */
    public static HashMap<String, Float> getMonthlyCategoricalData(
            HashMap<LocalDate, HashMap<String, Float>> emissionsData,
            LocalDate selectedDate,
            Context context) {

        HashMap<String, Float> categoryTotals = initializeCategoryTotals(context);
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

        return categoryTotals;
    }

    /**
     * Processes the categorical data for the yearly time period (3).
     * @param emissionsData The emissions data: {LocalDate: {Category: emission value}}
     * @param selectedDate The reference date (e.g., today).
     * @param context The context for accessing resources.
     * @return A HashMap of category totals.
     */
    public static HashMap<String, Float> getYearlyCategoricalData(
            HashMap<LocalDate, HashMap<String, Float>> emissionsData,
            LocalDate selectedDate,
            Context context) {

        HashMap<String, Float> categoryTotals = initializeCategoryTotals(context);

        for (Map.Entry<LocalDate, HashMap<String, Float>> entry : emissionsData.entrySet()) {
            LocalDate date = entry.getKey();
            if (date.getYear() == selectedDate.getYear()) {
                HashMap<String, Float> yearlyData = entry.getValue();
                if (yearlyData != null) {
                    for (String category : categoryTotals.keySet()) {
                        Float value = yearlyData.getOrDefault(category, 0f);
                        categoryTotals.put(category, categoryTotals.get(category) + value);
                    }
                }
            }
        }

        return categoryTotals;
    }

    /**
     * Initializes the category totals with 0 values.
     * @param context The context for accessing resources.
     * @return A HashMap of categories with initial values set to 0.
     */
    private static HashMap<String, Float> initializeCategoryTotals(Context context) {
        HashMap<String, Float> categoryTotals = new HashMap<>();
        String[] categories = context.getResources().getStringArray(R.array.Categories);
        for (String category : categories) {
            categoryTotals.put(category, 0f);
        }
        return categoryTotals;
    }

    /**
     * Processes the bar chart data for the weekly time period (1).
     * @param emissionsData The emissions data: {LocalDate: {Category: emission value}}
     * @param selectedDate The reference date (e.g., today).
     * @return An ArrayList<Float> of emissions totals for the period.
     */
    public static ArrayList<Float> getWeeklyChartData(
            HashMap<LocalDate, HashMap<String, Float>> emissionsData,
            LocalDate selectedDate) {

        ArrayList<Float> barChartData = new ArrayList<>();
        LocalDate startOfWeek = selectedDate.minusDays(selectedDate.getDayOfWeek().getValue() % 7); // Last Sunday
        int daysToInclude = selectedDate.getDayOfWeek().getValue(); // Number of days from Sunday to today

        for (int i = 0; i < daysToInclude; i++) {
            LocalDate currentDay = startOfWeek.plusDays(i);
            float dailyTotal = 0f;

            HashMap<String, Float> dayData = emissionsData.get(currentDay);
            if (dayData != null) {
                dailyTotal = dayData.values().stream().reduce(0f, Float::sum);
            }

            barChartData.add(dailyTotal);
        }

        return barChartData;
    }

    /**
     * Processes the bar chart data for the monthly time period (2).
     * @param emissionsData The emissions data: {LocalDate: {Category: emission value}}
     * @param selectedDate The reference date (e.g., today).
     * @return An ArrayList<Float> of emissions totals for the period.
     */
    public static ArrayList<Float> getMonthlyChartData(
            HashMap<LocalDate, HashMap<String, Float>> emissionsData,
            LocalDate selectedDate) {

        ArrayList<Float> barChartData = new ArrayList<>();
        LocalDate startOfMonth = selectedDate.withDayOfMonth(1);
        int daysInMonth = selectedDate.getDayOfMonth(); // Only consider days up to today

        for (int i = 0; i < daysInMonth; i++) {
            LocalDate currentDay = startOfMonth.plusDays(i);
            float dailyTotal = 0f;

            HashMap<String, Float> dayData = emissionsData.get(currentDay);
            if (dayData != null) {
                dailyTotal = dayData.values().stream().reduce(0f, Float::sum);
            }

            barChartData.add(dailyTotal);
        }

        return barChartData;
    }

    /**
     * Processes the bar chart data for the yearly time period (3).
     * @param emissionsData The emissions data: {LocalDate: {Category: emission value}}
     * @param selectedDate The reference date (e.g., today).
     * @return An ArrayList<Float> of emissions totals for the period.
     */
    public static ArrayList<Float> getYearlyChartData(
            HashMap<LocalDate, HashMap<String, Float>> emissionsData,
            LocalDate selectedDate) {

        ArrayList<Float> barChartData = new ArrayList<>();
        for (int month = 1; month <= selectedDate.getMonthValue(); month++) {
            float monthlyTotal = 0f;

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
