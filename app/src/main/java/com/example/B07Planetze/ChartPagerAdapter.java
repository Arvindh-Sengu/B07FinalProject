package com.example.b07demosummer2024;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class ChartPagerAdapter extends FragmentStateAdapter {

    private ArrayList<Float> data_em_by_categories;
    private ArrayList<Float> data_em_by_day;
    private int selectedTimePeriod;

    public ChartPagerAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<Float> data_em_by_categories, ArrayList<Float> data_em_by_day, int selectedTimePeriod) {
        super(fragmentActivity);
        this.data_em_by_categories = data_em_by_categories;
        this.data_em_by_day = data_em_by_day;
        this.selectedTimePeriod = selectedTimePeriod;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (selectedTimePeriod == 0){
            return PieChartFragment.newInstance(data_em_by_categories);
        }

        switch (position) {
            case 0:
                return BarChartFragment.newInstance(data_em_by_day);
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
