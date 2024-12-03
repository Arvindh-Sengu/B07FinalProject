package com.example.b07demosummer2024;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EcoHubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eco_hub);
        setTitle("Eco Hub");

        // Set up buttons for resources
        findViewById(R.id.btn_energy_star).setOnClickListener(v -> openWebsite("https://www.energystar.gov"));
        findViewById(R.id.btn_department_energy).setOnClickListener(v -> openWebsite("https://www.energy.gov"));
        findViewById(R.id.btn_carbon_trust).setOnClickListener(v -> openWebsite("https://www.carbontrust.com"));

        // Set up buttons for products
        findViewById(R.id.btn_sustainable_clothing).setOnClickListener(v -> openWebsite("https://www.thereformation.com"));
        findViewById(R.id.btn_energy_appliances).setOnClickListener(v -> openWebsite("https://www.energystar.gov/products"));
        findViewById(R.id.btn_smart_thermostats).setOnClickListener(v -> openWebsite("https://nest.com"));
        findViewById(R.id.btn_led_lights).setOnClickListener(v -> openWebsite("https://www.philips.com/led"));
        findViewById(R.id.btn_solar_panels).setOnClickListener(v -> openWebsite("https://www.tesla.com/solarpanels"));
    }

    // Method to open a website in a browser
    private void openWebsite(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
