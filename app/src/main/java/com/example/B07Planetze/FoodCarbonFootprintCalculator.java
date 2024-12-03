package com.example.B07Planetze;

public class FoodCarbonFootprintCalculator {

    // Method to calculate food emissions based on diet type
    public double calculateFoodEmissions(String dietType) {
        double emissions = 0;

        switch (dietType.toLowerCase()) {
            case "vegetarian":
                emissions = 1000; // kg CO2e per year for a vegetarian diet
                break;
            case "vegan":
                emissions = 500; // kg CO2e per year for a vegan diet
                break;
            case "pescatarian (fish/seafood)":
                emissions = 1500; // kg CO2e per year for a pescatarian diet
                break;
            case "meat-based (eat all types of animal products)":
                emissions = 0; // Meat-based will be calculated based on individual meat consumption
                break;
            default:
                emissions = 0;
                break;
        }

        return emissions;
    }

    // Method to calculate beef emissions based on frequency of consumption
    public double calculateBeefEmissions(String beefFrequency) {
        switch (beefFrequency.toLowerCase()) {
            case "daily":
                return 2500; // kg CO2e for daily beef consumption
            case "frequently (3-5 times/week)":
                return 1900; // kg CO2e for 3-5 times per week
            case "occasionally (1-2 times/week)":
                return 1300; // kg CO2e for 1-2 times per week
            case "never":
                return 0; // kg CO2e for never eating beef
            default:
                return 0;
        }
    }

    // Method to calculate pork emissions based on frequency of consumption
    public double calculatePorkEmissions(String porkFrequency) {
        switch (porkFrequency.toLowerCase()) {
            case "daily":
                return 1450; // kg CO2e for daily pork consumption
            case "frequently (3-5 times/week)":
                return 860; // kg CO2e for 3-5 times per week
            case "occasionally (1-2 times/week)":
                return 450; // kg CO2e for 1-2 times per week
            case "never":
                return 0; // kg CO2e for never eating pork
            default:
                return 0;
        }
    }

    // Method to calculate chicken emissions based on frequency of consumption
    public double calculateChickenEmissions(String chickenFrequency) {
        switch (chickenFrequency.toLowerCase()) {
            case "daily":
                return 950; // kg CO2e for daily chicken consumption
            case "frequently (3-5 times/week)":
                return 600; // kg CO2e for 3-5 times per week
            case "occasionally (1-2 times/week)":
                return 200; // kg CO2e for 1-2 times per week
            case "never":
                return 0; // kg CO2e for never eating chicken
            default:
                return 0;
        }
    }

    // Method to calculate fish emissions based on frequency of consumption
    public double calculateFishEmissions(String fishFrequency) {
        switch (fishFrequency.toLowerCase()) {
            case "daily":
                return 800; // kg CO2e for daily fish consumption
            case "frequently (3-5 times/week)":
                return 500; // kg CO2e for 3-5 times per week
            case "occasionally (1-2 times/week)":
                return 150; // kg CO2e for 1-2 times per week
            case "never":
                return 0; // kg CO2e for never eating fish
            default:
                return 0;
        }
    }

    // Method to calculate food waste emissions based on frequency of food waste
    public double calculateFoodWasteEmissions(String foodWasteFrequency) {
        switch (foodWasteFrequency.toLowerCase()) {
            case "never":
                return 0;
            case "rarely":
                return 23.4; // kg CO2e for rarely wasting food
            case "occasionally":
                return 70.2; // kg CO2e for occasionally wasting food
            case "frequently":
                return 140.4; // kg CO2e for frequently wasting food
            default:
                return 0;
        }
    }

    // Method to calculate the total food emissions based on user answers
    public double calculateTotalFoodEmissions(String dietType, String beefFrequency, String porkFrequency,
                                              String chickenFrequency, String fishFrequency, String foodWasteFrequency) {
        double foodEmissions = calculateFoodEmissions(dietType);

        // If meat-based diet, calculate based on meat consumption
        if ("meat-based (eat all types of animal products)".equalsIgnoreCase(dietType)) {
            double beefEmissions = calculateBeefEmissions(beefFrequency);
            double porkEmissions = calculatePorkEmissions(porkFrequency);
            double chickenEmissions = calculateChickenEmissions(chickenFrequency);
            double fishEmissions = calculateFishEmissions(fishFrequency);

            foodEmissions += beefEmissions + porkEmissions + chickenEmissions + fishEmissions;
        }

        // Add food waste emissions
        double foodWasteEmissions = calculateFoodWasteEmissions(foodWasteFrequency);
        foodEmissions += foodWasteEmissions;

        return foodEmissions;
    }
}
