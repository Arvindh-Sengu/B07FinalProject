package com.example.B07Planetze;

public class TransportationCarbonFootprintCalculator {

    // Method for car emissions based on the car type and distance
    public double calculateCarEmissions(String carType, String kmDrivenPerYear) {
        double emissionFactor = 0;
        switch (carType.toLowerCase()) {
            case "gasoline":
                emissionFactor = 0.24; // kg/km for gasoline
                break;
            case "diesel":
                emissionFactor = 0.27; // kg/km for diesel
                break;
            case "hybrid":
                emissionFactor = 0.16; // kg/km for hybrid
                break;
            case "electric":
                emissionFactor = 0.05; // kg/km for electric
                break;
            default:
                emissionFactor = 0;
                break;
        }

        double distance = convertKmRangeToValue(kmDrivenPerYear);
        return emissionFactor * distance;
    }

    private double convertKmRangeToValue(String kmRange) {
        switch (kmRange) {
            case "Up to 5,000 km (3,000 miles)":
                return 5000;
            case "5,000–10,000 km (3,000–6,000 miles)":
                return 10000;
            case "10,000–15,000 km (6,000–9,000 miles)":
                return 15000;
            case "15,000–20,000 km (9,000–12,000 miles)":
                return 20000;
            case "20,000–25,000 km (12,000–15,000 miles)":
                return 25000;
            case "More than 25,000 km (15,000 miles)":
                return 35000; // More than 25,000 km
            default:
                return 0;
        }
    }

    // Method for calculating public transport emissions based on frequency and time spent
    public double calculatePublicTransportEmissions(String frequency, String timeSpent) {
        double emissions = 0;
        if ("Never".equals(frequency)) {
            emissions = 0;
        } else if ("Occasionally (1-2 times/week)".equals(frequency)) {
            emissions = getPublicTransportEmissionForOccasionally(timeSpent);
        } else if ("Frequently (3-4 times/week)".equals(frequency)) {
            emissions = getPublicTransportEmissionForFrequently(timeSpent);
        } else if ("Always (5+ times/week)".equals(frequency)) {
            emissions = getPublicTransportEmissionForAlways(timeSpent);
        }
        return emissions;
    }

    private double getPublicTransportEmissionForOccasionally(String timeSpent) {
        switch (timeSpent) {
            case "Under 1 hour":
                return 246;
            case "1-3 hours":
                return 819;
            case "3-5 hours":
                return 1638;
            case "5-10 hours":
                return 3071;
            case "More than 10 hours":
                return 4095;
            default:
                return 0;
        }
    }

    private double getPublicTransportEmissionForFrequently(String timeSpent) {
        switch (timeSpent) {
            case "Under 1 hour":
                return 573;
            case "1-3 hours":
                return 1911;
            case "3-5 hours":
                return 3822;
            case "5-10 hours":
                return 7166;
            case "More than 10 hours":
                return 9555;
            default:
                return 0;
        }
    }

    private double getPublicTransportEmissionForAlways(String timeSpent) {
        switch (timeSpent) {
            case "Under 1 hour":
                return 573;
            case "1-3 hours":
                return 1911;
            case "3-5 hours":
                return 3822;
            case "5-10 hours":
                return 7166;
            case "More than 10 hours":
                return 9555;
            default:
                return 0;
        }
    }

    // Method for calculating short-haul flight emissions
    public double calculateShortHaulFlightsEmissions(String flightChoice) {
        switch (flightChoice) {
            case "None":
                return 0;
            case "1-2 flights":
                return 225;
            case "3-5 flights":
                return 600;
            case "6-10 flights":
                return 1200;
            case "More than 10 flights":
                return 1800;
            default:
                return 0;
        }
    }

    // Method for calculating long-haul flight emissions
    public double calculateLongHaulFlightsEmissions(String flightChoice) {
        switch (flightChoice) {
            case "None":
                return 0;
            case "1-2 flights":
                return 825;
            case "3-5 flights":
                return 2200;
            case "6-10 flights":
                return 4400;
            case "More than 10 flights":
                return 6600;
            default:
                return 0;
        }
    }

    // Method to calculate the total emissions based on user answers
    public double calculateTransportationEmissions(String carOwnership, String carType, String kmDrivenPerYear,
                                                   String publicTransportFrequency, String publicTransportTime,
                                                   String shortHaulFlights, String longHaulFlights) {

        // Car emissions if applicable
        double carEmissions = 0;
        if ("Yes".equals(carOwnership) && carType != null && !carType.isEmpty() && kmDrivenPerYear != null && !kmDrivenPerYear.isEmpty()) {
            carEmissions = calculateCarEmissions(carType, kmDrivenPerYear);
        }

        // Public transport emissions
        double publicTransportEmissions = calculatePublicTransportEmissions(publicTransportFrequency, publicTransportTime);

        // Flight emissions
        double shortHaulEmissions = calculateShortHaulFlightsEmissions(shortHaulFlights);
        double longHaulEmissions = calculateLongHaulFlightsEmissions(longHaulFlights);

        // Total emissions from all transportation sources
        return carEmissions + publicTransportEmissions + shortHaulEmissions + longHaulEmissions;
    }
}

