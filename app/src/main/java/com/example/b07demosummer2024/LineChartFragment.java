package com.example.b07demosummer2024;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class LineChartFragment extends Fragment {

    private ArrayList<Float> data;
    private int spinnerPosition; // Spinner position: 1 (Weekly), 2 (Monthly), 3 (Yearly)

    private LineChart lineChart;

    public static LineChartFragment newInstance(ArrayList<Float> data, int spinnerPosition) {
        LineChartFragment fragment = new LineChartFragment();
        Bundle args = new Bundle();
        args.putSerializable("data", data);
        args.putInt("spinnerPosition", spinnerPosition);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = (ArrayList<Float>) getArguments().getSerializable("data");
            spinnerPosition = getArguments().getInt("spinnerPosition");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_line_chart, container, false);
        lineChart = view.findViewById(R.id.lineChartView);
        setupLineChart();
        return view;
    }
    private List<String> getXAxisLabels() {
        switch (spinnerPosition) {
            case 1: // Weekly
                return List.of("S", "M", "T", "W", "T", "F", "S");
            case 2: // Monthly
                List<String> monthlyLabels = new ArrayList<>();
                for (int i = 1; i <= data.size(); i++) {
                    monthlyLabels.add(String.valueOf(i));
                }
                return monthlyLabels;
            case 3: // Yearly
                return List.of("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
            default:
                return new ArrayList<>(); // Fallback to an empty list
        }
    }
    private void setupLineChart() {
        ArrayList<Entry> entries = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            entries.add(new Entry(i, data.get(i))); // Create a new Entry for each data point
        }
        int color1 = ContextCompat.getColor(requireContext(), R.color.aquamarine);
        int color2 = ContextCompat.getColor(requireContext(), R.color.off_black);
        int color3 = ContextCompat.getColor(requireContext(), R.color.slate);

        LineDataSet dataSet = new LineDataSet(entries, "Emissions Data");
        dataSet.setColor(color2); // Customize line color
        dataSet.setValueTextColor(color3); // Customize value text color
        dataSet.setLineWidth(2f); // Customize line width
        dataSet.setCircleColor(color1);
        dataSet.setDrawValues(false);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);




        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(getXAxisLabels()));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTypeface(Typeface.DEFAULT_BOLD);
        xAxis.setTextColor(color1);

        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setTypeface(Typeface.DEFAULT_BOLD); // Make Y-axis labels bold
        yAxisLeft.setTextColor(color1); // Set the color of the Y-axis labels


        lineChart.getLegend().setEnabled(false); //hide legend
        lineChart.getDescription().setEnabled(false); // hide description
        lineChart.getAxisRight().setEnabled(false); // hide right axis
        lineChart.getAxisLeft().setDrawGridLines(false); // hide grid lines on left axis
        lineChart.getXAxis().setDrawGridLines(false); // hide grid lines on x axis

        lineChart.invalidate(); // Refresh the chart
    }
}
