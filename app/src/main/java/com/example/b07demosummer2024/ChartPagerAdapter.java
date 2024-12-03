package com.example.b07demosummer2024;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class ChartPagerAdapter extends FragmentStateAdapter {

    private HashMap<String, Float> categoricalData;
    private ArrayList<Float> PeriodicalData;

    private HashMap<String, Float> barchartStuff;
    private int selectedTimePeriod;

    private HashMap <LocalDate, HashMap <String, Float>> rawData;

    public ChartPagerAdapter(@NonNull FragmentActivity fragmentActivity, HashMap <LocalDate, HashMap <String, Float>> rawData, int selectedTimePeriod) {
        super(fragmentActivity);
        this.rawData = rawData;
        this.selectedTimePeriod = selectedTimePeriod;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (selectedTimePeriod == 0){



            return PieChartFragment.newInstance(categoricalData);
        }

        switch (position) {
            case 0:
                HashMap<String, Float> dummyData = new HashMap<>();
                dummyData.put("Transport", 5.0f);
                dummyData.put("Electricity", 7.5f);
                dummyData.put("Heating", 3.2f);
                dummyData.put("Waste", 4.0f);
                return BarChartFragment.newInstance(dummyData);
            case 1:
                return PieChartFragment.newInstance(data_em_by_categories);
            case 2:
                return LineChartFragment.newInstance(data_em_by_day);
            default:
                return PieChartFragment.newInstance(data_em_by_categories);
        }
    }

    @Override
    public int getItemCount() {
        return selectedTimePeriod == 0 ? 1 : 3; // Number of chart types
    }

//    public void updateData(ArrayList<Float> newData, int selectedTimePeriod) {
//        this.data = newData;
//        this.selectedTimePeriod = selectedTimePeriod;
//        notifyDataSetChanged();
//    }

}
