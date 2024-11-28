package com.example.b07demosummer2024;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import java.util.ArrayList;

public class PieChartFragment extends Fragment {

    private PieChart pieChart;

    public static PieChartFragment newInstance(ArrayList<Float> data) {
        PieChartFragment fragment = new PieChartFragment();
        Bundle args = new Bundle();
        args.putFloatArray("data", toPrimitive(data));  // Convert ArrayList<Float> to float[]
        fragment.setArguments(args);
        return fragment;
    }

    private static float[] toPrimitive(ArrayList<Float> data) {
        float[] primitiveData = new float[data.size()];
        for (int i = 0; i < data.size(); i++) {
            primitiveData[i] = data.get(i);
        }
        return primitiveData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pie_chart, container, false);
        pieChart = view.findViewById(R.id.pieChartView); // PieChart view

        // Get the data passed to this fragment
        float[] data = getArguments().getFloatArray("data");

        // Prepare the PieChart data
        ArrayList<PieEntry> entries = new ArrayList<>();
        String[] categories = {"Category 1", "Category 2", "Category 3"};

        for (int i = 0; i < data.length; i++) {
            entries.add(new PieEntry(data[i], categories[i]));
        }

        // Create a PieDataSet and assign it to the PieChart
        PieDataSet dataSet = new PieDataSet(entries, "Categories");
        dataSet.setColors(new int[]{getResources().getColor(android.R.color.holo_blue_light),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_red_light)});
        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);

        // Refresh the chart
        pieChart.invalidate();

        return view;
    }
}
