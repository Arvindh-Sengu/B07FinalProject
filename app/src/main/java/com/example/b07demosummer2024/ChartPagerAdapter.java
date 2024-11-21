package com.example.b07demosummer2024;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class ChartPagerAdapter extends RecyclerView.Adapter<ChartPagerAdapter.ChartViewHolder> {

    private Context context;

    public ChartPagerAdapter(Context context) {
        this.context = context;
    }


    @Override
    public ChartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;

        if (viewType == 0) {
            view = inflater.inflate(R.layout.item_bar_chart, parent, false);
        } else if (viewType == 1) {
            view = inflater.inflate(R.layout.item_pie_chart, parent, false);
        } else {
            view = inflater.inflate(R.layout.item_line_chart, parent, false);
        }
        return new ChartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChartViewHolder holder, int position) {
        if (position == 0) {
            setupBarChart((BarChart) holder.chartView);
        } else if (position == 1) {
            setupPieChart((PieChart) holder.chartView);
        } else {
            setupLineChart((LineChart) holder.chartView);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class ChartViewHolder extends RecyclerView.ViewHolder {
        View chartView;

        public ChartViewHolder(View itemView) {
            super(itemView);
            chartView = itemView.findViewById(R.id.chartView); // Find chart view reference
        }
    }

    private void setupBarChart(BarChart barChart) {

        ArrayList<BarEntry> entries = new ArrayList<>();
        //test entries
        entries.add(new BarEntry(0f, 40f));
        entries.add(new BarEntry(1f, 50f));
        entries.add(new BarEntry(2f, 70f));

        BarDataSet dataSet = new BarDataSet(entries, "Bar Chart Data");
        dataSet.setColor(context.getResources().getColor(android.R.color.holo_blue_light));

        BarData barData = new BarData(dataSet);
        barChart.setData(barData);
        barChart.invalidate();
    }

    private void setupPieChart(PieChart pieChart) {

        ArrayList<PieEntry> entries = new ArrayList<>();
        //test entries
        entries.add(new PieEntry(40f, "Category 1"));
        entries.add(new PieEntry(30f, "Category 2"));
        entries.add(new PieEntry(30f, "Category 3"));

        PieDataSet dataSet = new PieDataSet(entries, "Pie Chart Data");

        dataSet.setColors(new int[] {
                context.getResources().getColor(android.R.color.holo_blue_light),
                context.getResources().getColor(android.R.color.holo_green_light),
                context.getResources().getColor(android.R.color.holo_orange_light)
        });

        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);


        pieChart.invalidate();
    }

    private void setupLineChart(LineChart lineChart) {
       //test entries
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0f, 10f));
        entries.add(new Entry(1f, 20f));
        entries.add(new Entry(2f, 30f));

        LineDataSet dataSet = new LineDataSet(entries, "Line Chart Data");
        dataSet.setColor(context.getResources().getColor(android.R.color.holo_red_light));

        // Create LineData and set to chart
        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate(); // Refresh the chart
    }
}
