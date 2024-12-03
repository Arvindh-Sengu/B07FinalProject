package com.example.b07demosummer2024;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

public class LineChartFragment extends Fragment {

    private ArrayList<Float> data;

    public static LineChartFragment newInstance(ArrayList<Float> data) {
        LineChartFragment fragment = new LineChartFragment();
        Bundle args = new Bundle();
        args.putSerializable("data", data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = (ArrayList<Float>) getArguments().getSerializable("data");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_line_chart, container, false);

        LineChart lineChart = view.findViewById(R.id.lineChartView);
        setupLineChart(lineChart);

        lineChart.getDescription().setEnabled(false);

        // Disable the legend
        lineChart.getLegend().setEnabled(false);

        // Remove grid lines (both vertical and horizontal)
        lineChart.getXAxis().setDrawGridLines(false);  // Remove vertical grid lines
        lineChart.getAxisLeft().setDrawGridLines(false); // Remove horizontal grid lines
        lineChart.getAxisRight().setDrawGridLines(false); // Remove horizontal grid lines on the right axis

        // Disable the right Y-Axis
        lineChart.getAxisRight().setEnabled(false);

        // Ensure X-Axis labels appear at the bottom
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        final String[] months = {
                "Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        };

// Custom ValueFormatter for months
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                // Ensure the value is within bounds of the months array
                int index = (int) value;
                if (index >= 0 && index < months.length) {
                    return months[index];
                }
                return ""; // Return empty string for out-of-bounds values
            }
        });



        return view;
    }


    private void setupLineChart(LineChart lineChart) {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            entries.add(new Entry(i, data.get(i)));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Line Chart");
        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate(); // Refresh chart
    }
}
