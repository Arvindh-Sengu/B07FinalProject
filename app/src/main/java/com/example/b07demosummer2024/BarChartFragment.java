package com.example.b07demosummer2024;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BarChartFragment extends Fragment {

    private ArrayList<Float> chartData; // Data to display
    private int spinnerPosition; // Spinner position: 1 (Weekly), 2 (Monthly), 3 (Yearly)
    private BarChart barChart;

    public static BarChartFragment newInstance(ArrayList<Float> chartData, int spinnerPosition) {
        BarChartFragment fragment = new BarChartFragment();
        Bundle args = new Bundle();
        args.putSerializable("chartData", chartData);
        args.putInt("spinnerPosition", spinnerPosition);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            chartData = (ArrayList<Float>) getArguments().getSerializable("chartData");
            spinnerPosition = getArguments().getInt("spinnerPosition");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bar_chart, container, false);
        barChart = view.findViewById(R.id.barChartView);
        setupBarChart();
        return view;
    }

    private void setupBarChart() {
        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < chartData.size(); i++) {
            entries.add(new BarEntry(i, chartData.get(i)));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Emissions Data");

        int color1 = ContextCompat.getColor(requireContext(), R.color.aquamarine);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(ContextCompat.getColor(requireContext(), R.color.moonstone));
        colors.add(ContextCompat.getColor(requireContext(), R.color.gunmetal));

        dataSet.setColors(colors); // Customize bar colors

        BarData data = new BarData(dataSet);
        barChart.setData(data);
        dataSet.setDrawValues(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(getXAxisLabels()));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(color1);



        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setTextColor(color1); // Set the color of the Y-axis labels



        barChart.getLegend().setEnabled(false);
        barChart.getDescription().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getXAxis().setDrawGridLines(false);


        barChart.invalidate(); // Refresh the chart
    }

    private List<String> getXAxisLabels() {
        switch (spinnerPosition) {
            case 1: // Weekly
                return List.of("S", "M", "T", "W", "T", "F", "S");
            case 2: // Monthly
                List<String> monthlyLabels = new ArrayList<>();
                for (int i = 1; i <= chartData.size(); i++) {
                    monthlyLabels.add(String.valueOf(i));
                }
                return monthlyLabels;
            case 3: // Yearly
                return List.of("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
            default:
                return new ArrayList<>(); // Fallback to an empty list
        }
    }
    // Interface to pass data to the activity
    public interface DataListener {
        void onDataSelected(HashMap<String, Float> emissionDetails);
    }
}
