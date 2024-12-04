package com.example.b07demosummer2024;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class ChartPagerAdapter extends FragmentStateAdapter {

    private final Context context; // Context for categorical data
    private final HashMap<LocalDate, HashMap<String, Float>> rawData; // Raw emissions data
    private final int selectedTimePeriod; // Spinner selection (0: Weekly, 1: Monthly, 2: Yearly)
    private final LocalDate selectedDate; // Reference date (e.g., today)

    public ChartPagerAdapter(@NonNull FragmentActivity fragmentActivity,
                             HashMap<LocalDate, HashMap<String, Float>> rawData,
                             int selectedTimePeriod,
                             LocalDate selectedDate) {
        super(fragmentActivity);
        this.rawData = rawData;
        this.context = fragmentActivity;
        this.selectedTimePeriod = selectedTimePeriod;
        this.selectedDate = selectedDate;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (selectedTimePeriod) {
            case 0:
                HashMap<String, Float> dailyPieChartData = DataProcessor.getDailyCategoricalData(rawData, selectedDate, context);
                return PieChartFragment.newInstance(dailyPieChartData);

            case 1:
                switch (position) {
                    case 0: // Bar Chart
                        ArrayList<Float> weeklyBarChartData = DataProcessor.getWeeklyChartData(rawData, selectedDate);
                        return BarChartFragment.newInstance(weeklyBarChartData, 1);

                    case 1: // Pie Chart
                        HashMap<String, Float> weeklyPieChartData = DataProcessor.getWeeklyCategoricalData(rawData, selectedDate, context);
                        return PieChartFragment.newInstance(weeklyPieChartData);

                    case 2: // Line Chart
                        ArrayList<Float> weeklyLineChartData = DataProcessor.getWeeklyChartData(rawData, selectedDate);
                        return LineChartFragment.newInstance(weeklyLineChartData, 1);

                    default:
                        throw new IllegalArgumentException("Invalid chart position");
                }

            case 2:
                switch (position) {
                    case 0: // Bar Chart
                        ArrayList<Float> monthlyBarChartData = DataProcessor.getMonthlyChartData(rawData, selectedDate);
                        return BarChartFragment.newInstance(monthlyBarChartData, 2);

                    case 1: // Pie Chart
                        HashMap<String, Float> monthlyPieChartData = DataProcessor.getMonthlyCategoricalData(rawData, selectedDate, context);
                        return PieChartFragment.newInstance(monthlyPieChartData);

                    case 2: // Line Chart
                        ArrayList<Float> monthlyLineChartData = DataProcessor.getMonthlyChartData(rawData, selectedDate);
                        return LineChartFragment.newInstance(monthlyLineChartData, 2);

                    default:
                        throw new IllegalArgumentException("Invalid chart position");
                }

            case 3:
                switch (position) {
                    case 0: // Bar Chart
                        ArrayList<Float> yearlyBarChartData = DataProcessor.getYearlyChartData(rawData, selectedDate);
                        return BarChartFragment.newInstance(yearlyBarChartData, 3);

                    case 1: // Pie Chart
                        HashMap<String, Float> yearlyPieChartData = DataProcessor.getYearlyCategoricalData(rawData, selectedDate, context);
                        return PieChartFragment.newInstance(yearlyPieChartData);

                    case 2: // Line Chart
                        ArrayList<Float> yearlyLineChartData = DataProcessor.getLineChartYearlyData(rawData, selectedDate);
                        return LineChartFragment.newInstance(yearlyLineChartData, 3);

                    default:
                        throw new IllegalArgumentException("Invalid chart position");
                }

            default:
                throw new IllegalArgumentException("Invalid time period");
        }


    }

    @Override
    public int getItemCount() {
        if (selectedTimePeriod == 0) {
            return 1;
        }

        return 3; // Number of chart types (Bar, Pie, Line)
    }


}
