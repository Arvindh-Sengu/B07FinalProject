package com.example.B07Planetze;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.B07Planetze.R;

import java.util.HashMap;
import java.util.Map;

public class InfoCardFragment extends Fragment {

    private TableLayout infoTable;

    public static InfoCardFragment newInstance(HashMap<String, Float> data) {
        InfoCardFragment fragment = new InfoCardFragment();
        Bundle args = new Bundle();
        args.putSerializable("data", data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_card, container, false);
        infoTable = view.findViewById(R.id.infoTable);

        if (getArguments() != null) {
            HashMap<String, Float> data = (HashMap<String, Float>) getArguments().getSerializable("data");
            populateTable(data);
        }

        return view;
    }

    void populateTable(HashMap<String, Float> data) {
        infoTable.removeAllViews(); // Clear existing rows

        for (Map.Entry<String, Float> entry : data.entrySet()) {
            TableRow row = new TableRow(getContext());
            TextView keyView = new TextView(getContext());
            TextView valueView = new TextView(getContext());

            keyView.setText(entry.getKey());
            valueView.setText(String.valueOf(entry.getValue()));

            keyView.setPadding(8, 8, 8, 8);
            valueView.setPadding(8, 8, 8, 8);

            row.addView(keyView);
            row.addView(valueView);

            infoTable.addView(row);
        }
    }
}