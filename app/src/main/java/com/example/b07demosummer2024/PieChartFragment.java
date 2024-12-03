package com.example.b07demosummer2024;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;

public class PieChartFragment extends Fragment {

    private HashMap<String, Float> categoryData; // Key: Category, Value: Emission
    private PieChart pieChart;

    public static PieChartFragment newInstance(HashMap<String, Float> categoryData) {
        PieChartFragment fragment = new PieChartFragment();
        Bundle args = new Bundle();
        args.putSerializable("categoryData", categoryData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryData = (HashMap<String, Float>) getArguments().getSerializable("categoryData");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pie_chart, container, false);
        pieChart = view.findViewById(R.id.pieChartView); // PieChart view
        setupPieChart();
        return view;
    }

    private void setupPieChart() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (String category : categoryData.keySet()) {
            entries.add(new PieEntry(categoryData.get(category), category));
        }

        // Create a PieDataSet
        PieDataSet dataSet = new PieDataSet(entries, "Emissions by Category");
        dataSet.setColors(new int[]{
                getResources().getColor(android.R.color.holo_blue_light),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_red_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_purple)
        }); // Customize colors

        // Assign PieData to PieChart
        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);

        // Customize PieChart appearance
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawEntryLabels(true);
        pieChart.setUsePercentValues(true);
        pieChart.setHoleRadius(40f);
        pieChart.setTransparentCircleRadius(45f);

        // Refresh the chart
        pieChart.invalidate();
    }
}
