package com.example.B07Planetze;


import androidx.viewpager2.widget.ViewPager2;

public class ViewPagerListener extends ViewPager2.OnPageChangeCallback {

    public interface OnPageSelectedListener {
        void onPageSelected(int position);
    }

    private final OnPageSelectedListener listener;

    public ViewPagerListener(OnPageSelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
        if (listener != null) {
            listener.onPageSelected(position);
        }
    }
}
