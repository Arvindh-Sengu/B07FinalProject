package com.example.B07Planetze;

public class ConsumptionCarbonFootprintCalculator {

    // Method to calculate emissions from clothing purchases
    public double calculateClothingEmissions(String clothingFrequency) {
        switch (clothingFrequency.toLowerCase()) {
            case "monthly": return 360.0;
            case "quarterly": return 120.0;
            case "annually": return 100.0;
            case "rarely": return 5.0;
            default: return 0.0;
        }
    }

    // Method to calculate reductions based on eco-friendly product usage
    public double calculateEcoFriendlyReduction(String ecoFriendlyProducts, double baseEmissions) {
        switch (ecoFriendlyProducts.toLowerCase()) {
            case "yes, regularly": return -0.5 * baseEmissions; // Reduce by 50%
            case "yes, occasionally": return -0.3 * baseEmissions; // Reduce by 30%
            case "no": return 0.0; // No reduction
            default: return 0.0;
        }
    }

    // Method to calculate emissions from electronic device purchases
    public double calculateElectronicsEmissions(String electronicDevices) {
        switch (electronicDevices.toLowerCase()) {
            case "none": return 0.0;
            case "1": return 300.0;
            case "2": return 600.0;
            case "3 or more": return 1050.0;
            default: return 0.0;
        }
    }

    // Method to calculate reductions based on recycling frequency
    public double calculateRecyclingReduction(String recyclingFrequency, String clothingFrequency, String electronicDevices) {
        double clothingReduction = 0.0;
        double electronicsReduction = 0.0;

        // Clothing recycling reduction
        switch (clothingFrequency.toLowerCase()) {
            case "monthly":
                if (recyclingFrequency.equalsIgnoreCase("occasionally")) {
                    clothingReduction = -54.0;
                } else if (recyclingFrequency.equalsIgnoreCase("frequently")) {
                    clothingReduction = -108.0;
                } else if (recyclingFrequency.equalsIgnoreCase("always")) {
                    clothingReduction = -180.0;
                }
                break;

            case "annually":
                if (recyclingFrequency.equalsIgnoreCase("occasionally")) {
                    clothingReduction = -15.0;
                } else if (recyclingFrequency.equalsIgnoreCase("frequently")) {
                    clothingReduction = -30.0;
                } else if (recyclingFrequency.equalsIgnoreCase("always")) {
                    clothingReduction = -50.0;
                }
                break;

            case "rarely":
                if (recyclingFrequency.equalsIgnoreCase("occasionally")) {
                    clothingReduction = -0.75;
                } else if (recyclingFrequency.equalsIgnoreCase("frequently")) {
                    clothingReduction = -1.5;
                } else if (recyclingFrequency.equalsIgnoreCase("always")) {
                    clothingReduction = -2.5;
                }
                break;
        }

        // Electronics recycling reduction
        switch (electronicDevices.toLowerCase()) {
            case "1":
                if (recyclingFrequency.equalsIgnoreCase("occasionally")) {
                    electronicsReduction = -45.0;
                } else if (recyclingFrequency.equalsIgnoreCase("frequently")) {
                    electronicsReduction = -60.0;
                } else if (recyclingFrequency.equalsIgnoreCase("always")) {
                    electronicsReduction = -90.0;
                }
                break;

            case "2":
                if (recyclingFrequency.equalsIgnoreCase("occasionally")) {
                    electronicsReduction = -60.0;
                } else if (recyclingFrequency.equalsIgnoreCase("frequently")) {
                    electronicsReduction = -120.0;
                } else if (recyclingFrequency.equalsIgnoreCase("always")) {
                    electronicsReduction = -180.0;
                }
                break;

            case "3 or more":
                if (recyclingFrequency.equalsIgnoreCase("occasionally")) {
                    electronicsReduction = -105.0;
                } else if (recyclingFrequency.equalsIgnoreCase("frequently")) {
                    electronicsReduction = -210.0;
                } else if (recyclingFrequency.equalsIgnoreCase("always")) {
                    electronicsReduction = -315;
                }
                break;
        }

        return clothingReduction + electronicsReduction;
    }

    // Method to calculate total consumption emissions
    public double calculateTotalConsumptionEmissions(String clothingFrequency, String ecoFriendlyProducts,
                                                     String electronicDevices, String recyclingFrequency) {
        // Base clothing emissions
        double clothingEmissions = calculateClothingEmissions(clothingFrequency);

        // Reduction from eco-friendly product usage
        double ecoFriendlyReduction = calculateEcoFriendlyReduction(ecoFriendlyProducts, clothingEmissions);

        // Emissions from electronic devices
        double electronicsEmissions = calculateElectronicsEmissions(electronicDevices);

        // Recycling reductions
        double recyclingReduction = calculateRecyclingReduction(recyclingFrequency, clothingFrequency, electronicDevices);

        // Total emissions
        double totalEmissions = clothingEmissions + ecoFriendlyReduction + electronicsEmissions + recyclingReduction;

        // Ensure emissions are not negative
        return Math.max(totalEmissions, 0.0);
    }
}
