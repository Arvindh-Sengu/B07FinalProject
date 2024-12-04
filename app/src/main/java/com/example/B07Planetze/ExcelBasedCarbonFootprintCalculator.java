package com.example.B07Planetze;

import android.content.Context;

import java.io.IOException;
import com.B07Planetze.R;

public class ExcelBasedCarbonFootprintCalculator implements CarbonFootprintCalculator {

    Context context;
    String[] answers;

    public ExcelBasedCarbonFootprintCalculator(Context context, String[] answers) {
        this.context = context;
        this.answers = answers;
    }
    // Calculates transportation-related carbon emissions.
    @Override
    public double transportationEmissionCalculations() {
        TransportationCarbonFootprintCalculator calculator = new TransportationCarbonFootprintCalculator();
        String hasCar = answers[0];
        String typeCar = answers[1];
        String kmPerYear = answers[2];
        String publicTransportationFrequency = answers[3];
        String publicTransportationTime = answers[4];
        String shortHaulFlights = answers[5];
        String longHaulFlights = answers[6];
        return calculator.calculateTransportationEmissions(
                hasCar, typeCar, kmPerYear, publicTransportationFrequency, publicTransportationTime, shortHaulFlights, longHaulFlights
        );
    }

    // Calculates food-related carbon emissions.
    @Override
    public double foodEmissionCalculations() {
        FoodCarbonFootprintCalculator calculator = new FoodCarbonFootprintCalculator();
        String dietType = answers[7];
        String beefConsumptionFrequency = answers[8];
        String porkConsumptionFrequency = answers[9];
        String chickenConsumptionFrequency = answers[10];
        String fishConsumptionFrequency = answers[11];
        String foodWasteFrequency = answers[12];

        return calculator.calculateTotalFoodEmissions(
                dietType, beefConsumptionFrequency, porkConsumptionFrequency, chickenConsumptionFrequency, fishConsumptionFrequency, foodWasteFrequency
        );
    }

    // Calculates housing-related carbon emissions.
    @Override
    public double housingEmissionCalculations() {
        double housingEmissions = 0;
        try {
            HousingCarbonFootprintCalculator calculator = new HousingCarbonFootprintCalculator(context, R.raw.housing_emissions);
            String homeType = answers[13];
            String occupants = answers[14];
            String homeSize = answers[15];
            String homeHeatingSource = answers[16];
            String electricityBill = answers[17];
            String waterHeatingSource = answers[18];
            String renewableEnergyUsage = answers[19];

            housingEmissions = calculator.calculateHousingEmisssions(
                    homeType, occupants, homeSize, homeHeatingSource, electricityBill, waterHeatingSource, renewableEnergyUsage
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return housingEmissions;
    }

    // Calculates consumption-related carbon emissions.
    @Override
    public double consumptionEmissionCalculations() {
        ConsumptionCarbonFootprintCalculator calculator = new ConsumptionCarbonFootprintCalculator();
        String clothingFrequency = answers[20];
        String ecoFriendlyProducts = answers[21];
        String electronicDevices = answers[22];
        String recyclingFrequency = answers[23];

        return calculator.calculateTotalConsumptionEmissions(
                clothingFrequency, ecoFriendlyProducts, electronicDevices, recyclingFrequency
        );
    }
}
