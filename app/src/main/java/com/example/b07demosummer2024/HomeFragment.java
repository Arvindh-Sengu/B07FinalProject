package com.example.b07demosummer2024;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home_fragment, container, false);

        Button buttonEcoTracker = view.findViewById(R.id.Eco_Tracker);
        Button buttonEcoGauge = view.findViewById(R.id.Eco_Gauge);
        Button buttonEcoBalance = view.findViewById(R.id.Eco_Balance);
        Button buttonEcoHub = view.findViewById(R.id.Eco_Hub);
        Button buttonEcoAgent = view.findViewById(R.id.Eco_Agent);

        buttonEcoTracker.setOnClickListener(v -> startActivity(new Intent(getActivity(), EcoTrackerActivity.class)));
        buttonEcoGauge.setOnClickListener(v -> startActivity(new Intent(getActivity(), EcoGaugeActivity.class)));
        buttonEcoBalance.setOnClickListener(v -> startActivity(new Intent(getActivity(), EcoBalanceActivity.class)));
        buttonEcoHub.setOnClickListener(v -> startActivity(new Intent(getActivity(), EcoHubActivity.class)));
        buttonEcoAgent.setOnClickListener(v -> startActivity(new Intent(getActivity(), EcoAgentActivity.class)));

        return view;
    }
}
