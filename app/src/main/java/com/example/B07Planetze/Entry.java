package com.example.B07Planetze;

public class Entry {
    // Fields for transportation, food, and shopping activities...
    private float distanceDriven;
    private float timeSpentOnPublicTransport;
    private float distanceCycledOrWalked;
    private int numberOfFlights;

    private int chickenServings;
    private int beefServings;
    private int vegetarianServings;
    private int seafoodServings;
    private int porkServings;

    private float amountSpentOnClothes;
    private float amountSpentOnElectronics;
    private float amountSpentOnBills;

    // Constants for emission factors (approximations)
    private static final float CO2_PER_KM_DRIVEN = 0.233f; // kg CO2 per km for personal vehicles
    private static final float CO2_PER_KM_PUBLIC_TRANSPORT = 0.105f; // kg CO2 per km for public transport
    private static final float CO2_PER_KM_FLIGHT = 0.09f; // kg CO2 per km for flights
    private static final float CO2_PER_KM_CYCLE_WALK = 0.0f; // No emissions for cycling or walking

    public Entry(float distanceDriven, float timeSpentOnPublicTransport, float distanceCycledOrWalked, int numberOfFlights, int chickenServings, int beefServings, int porkServings, int seafoodServings, int vegetarianServings, float amountSpentOnBills, float amountSpentOnClothes, float amountSpentOnElectronics) {
        this.distanceDriven = distanceDriven;
        this.timeSpentOnPublicTransport = timeSpentOnPublicTransport;
        this.distanceCycledOrWalked = distanceCycledOrWalked;
        this.numberOfFlights = numberOfFlights;
        this.chickenServings = chickenServings;
        this.beefServings = beefServings;
        this.seafoodServings = seafoodServings;
        this.vegetarianServings = vegetarianServings;
        this.porkServings = porkServings;
        this.amountSpentOnBills = amountSpentOnBills;
        this.amountSpentOnClothes = amountSpentOnClothes;
        this.amountSpentOnElectronics = amountSpentOnElectronics;
    }

    // Methods for calculating CO2 emissions
    public float calculateTransportationCO2() {
        float transportationCO2 = 0f;

        // Calculate emissions for distance driven
        transportationCO2 += distanceDriven * CO2_PER_KM_DRIVEN;

        // Calculate emissions for time spent on public transport
        // (Assume timeSpentOnPublicTransport corresponds to distance traveled)
        transportationCO2 += timeSpentOnPublicTransport * CO2_PER_KM_PUBLIC_TRANSPORT;

        // Calculate emissions for flights
        transportationCO2 += numberOfFlights * CO2_PER_KM_FLIGHT;

        // No emissions for cycling or walking
        transportationCO2 += distanceCycledOrWalked * CO2_PER_KM_CYCLE_WALK;

        return transportationCO2;
    }

    // Methods for calculating food-related CO2 emissions

    private static final float CO2_PER_SERVING_CHICKEN = 2.5f; // kg CO2 per serving
    private static final float CO2_PER_SERVING_BEEF = 3.5f; // kg CO2 per serving
    private static final float CO2_PER_SERVING_VEGETARIAN = 0.5f; // kg CO2 per serving
    private static final float CO2_PER_SERVING_SEAFOOD = 2.0f; // kg CO2 per serving
    private static final float CO2_PER_SERVING_PORK = 2.0f; // kg CO2 per serving

    public float calculateFoodCO2() {
        float foodCO2 = 0f;

        // Calculate emissions for each food type based on servings
        foodCO2 += chickenServings * CO2_PER_SERVING_CHICKEN;
        foodCO2 += beefServings * CO2_PER_SERVING_BEEF;
        foodCO2 += vegetarianServings * CO2_PER_SERVING_VEGETARIAN;
        foodCO2 += seafoodServings * CO2_PER_SERVING_SEAFOOD;
        foodCO2 += porkServings * CO2_PER_SERVING_PORK;

        return foodCO2;
    }

    // Methods for calculating shopping-related CO2 emissions

    private static final float CO2_PER_DOLLAR_CLOTHES = 0.04f; // kg CO2 per dollar spent on clothes
    private static final float CO2_PER_DOLLAR_ELECTRONICS = 0.2f; // kg CO2 per dollar spent on electronics
    private static final float CO2_PER_DOLLAR_BILLS = 0.15f; // kg CO2 per dollar spent on bills (energy consumption)

    public float calculateShoppingCO2() {
        float shoppingCO2 = 0f;

        // Calculate emissions based on spending in different categories
        shoppingCO2 += amountSpentOnClothes * CO2_PER_DOLLAR_CLOTHES;
        shoppingCO2 += amountSpentOnElectronics * CO2_PER_DOLLAR_ELECTRONICS;
        shoppingCO2 += amountSpentOnBills * CO2_PER_DOLLAR_BILLS;

        return shoppingCO2;
    }

    // Method to calculate total carbon footprint for the entry (sum of transportation, food, and shopping)
    public float calculateTotalCO2() {
        float transportationCO2 = calculateTransportationCO2();
        float foodCO2 = calculateFoodCO2();
        float shoppingCO2 = calculateShoppingCO2();

        return transportationCO2 + foodCO2 + shoppingCO2;
    }
}
