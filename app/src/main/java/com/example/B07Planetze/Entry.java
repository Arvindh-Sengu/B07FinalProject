package com.example.B07Planetze;

import java.util.HashMap;

/**
 * Represents an individual activity entry for tracking daily carbon footprint.
 * Each entry includes specific activity data (e.g., transportation, meals, shopping)
 * and calculates the associated CO2e emissions.
 */
public class Entry {
    // Fields
    /**
     * The type of activity (e.g., "Drive Personal Vehicle", "Meal", "Buy Electronics").
     */
    private String activityType;

    /**
     * Total CO2e emissions for the entry.
     */
    private float totalCO2e;

    /**
     * CO2e emissions from transportation-related activities.
     */
    private float transportationEmissions;

    /**
     * CO2e emissions from meals.
     */
    private float mealEmissions;

    /**
     * CO2e emissions from shopping-related activities.
     */
    private float shoppingEmissions;

    // Activity-specific fields
    /**
     * Distance driven in kilometers for transportation activities.
     */
    private float distanceDriven;

    /**
     * A map tracking distances traveled via public transit (bus, train, subway).
     */
    private HashMap<String, Float> transitDistances;

    /**
     * Distance traveled by cycling or walking in kilometers.
     */
    private float distanceCycledOrWalked;

    /**
     * A map tracking the number of short-haul and long-haul flights taken.
     */
    private HashMap<String, Integer> flightCounts;

    /**
     * A map tracking servings of different meal types (e.g., beef, pork, chicken, plant-based).
     */
    private HashMap<String, Integer> foodServings;

    /**
     * Number of clothing items purchased.
     */
    private int numberOfClothingBought;

    /**
     * A map tracking the number of electronics purchased (e.g., smartphone, laptop, TV).
     */
    private HashMap<String, Integer> electronicsPurchased;

    /**
     * A map tracking other types of purchases (e.g., furniture, appliances).
     */
    private HashMap<String, Integer> otherPurchases;

    /**
     * A map tracking energy bills (electricity, gas, water) in dollars.
     */
    private HashMap<String, Float> energyBills;

    // Constructor
    /**
     * Constructs a new Entry object for a given activity type, initializing all fields with default values.
     *
     * @param activityType The type of activity this entry represents.
     */
    public Entry(String activityType) {
        this.activityType = activityType;

        this.transitDistances = new HashMap<>();
        this.transitDistances.put("bus", 0f);
        this.transitDistances.put("train", 0f);
        this.transitDistances.put("subway", 0f);

        this.flightCounts = new HashMap<>();
        this.flightCounts.put("short-haul", 0);
        this.flightCounts.put("long-haul", 0);

        this.foodServings = new HashMap<>();
        this.foodServings.put("beef", 0);
        this.foodServings.put("pork", 0);
        this.foodServings.put("chicken", 0);
        this.foodServings.put("fish", 0);
        this.foodServings.put("plant-based", 0);

        this.electronicsPurchased = new HashMap<>();
        this.electronicsPurchased.put("smartphone", 0);
        this.electronicsPurchased.put("laptop", 0);
        this.electronicsPurchased.put("tv", 0);
        this.electronicsPurchased.put("tablet", 0);
        this.electronicsPurchased.put("other", 0);

        this.otherPurchases = new HashMap<>();
        this.otherPurchases.put("furniture", 0);
        this.otherPurchases.put("appliances", 0);
        this.otherPurchases.put("miscellaneous", 0);

        this.energyBills = new HashMap<>();
        this.energyBills.put("electricity", 0f);
        this.energyBills.put("gas", 0f);
        this.energyBills.put("water", 0f);
    }

    // Methods

    /**
     * Calculates CO2e emissions for transportation activities.
     * Includes emissions from driving, public transit, and flights.
     */
    public void calculateTransportationEmissions() {
        float vehicleEmissionFactor = 0.2f; // CO2e per km driven
        float busEmissionFactor = 0.1f; // CO2e per km
        float trainEmissionFactor = 0.05f; // CO2e per km
        float subwayEmissionFactor = 0.04f; // CO2e per km

        transportationEmissions = 0;
        transportationEmissions += distanceDriven * vehicleEmissionFactor;
        transportationEmissions += transitDistances.get("bus") * busEmissionFactor;
        transportationEmissions += transitDistances.get("train") * trainEmissionFactor;
        transportationEmissions += transitDistances.get("subway") * subwayEmissionFactor;

        float shortHaulEmissionFactor = 0.15f; // CO2e per short-haul flight
        float longHaulEmissionFactor = 0.25f; // CO2e per long-haul flight

        transportationEmissions += flightCounts.get("short-haul") * shortHaulEmissionFactor;
        transportationEmissions += flightCounts.get("long-haul") * longHaulEmissionFactor;
    }

    /**
     * Calculates CO2e emissions for meal consumption based on food servings.
     */
    public void calculateMealEmissions() {
        float beefEmissionFactor = 27.0f; // CO2e per serving
        float porkEmissionFactor = 12.1f; // CO2e per serving
        float chickenEmissionFactor = 6.9f; // CO2e per serving
        float fishEmissionFactor = 6.1f; // CO2e per serving
        float plantBasedEmissionFactor = 2.0f; // CO2e per serving

        mealEmissions = 0;
        mealEmissions += foodServings.get("beef") * beefEmissionFactor;
        mealEmissions += foodServings.get("pork") * porkEmissionFactor;
        mealEmissions += foodServings.get("chicken") * chickenEmissionFactor;
        mealEmissions += foodServings.get("fish") * fishEmissionFactor;
        mealEmissions += foodServings.get("plant-based") * plantBasedEmissionFactor;
    }

    /**
     * Calculates CO2e emissions for shopping activities.
     * Includes clothing, electronics, and other purchases.
     */
    public void calculateShoppingEmissions() {
        float clothingEmissionFactor = 5.0f; // CO2e per clothing item
        float electronicsEmissionFactor = 20.0f; // CO2e per electronic device
        float otherPurchaseEmissionFactor = 10.0f; // CO2e per other purchase

        shoppingEmissions = 0;
        shoppingEmissions += numberOfClothingBought * clothingEmissionFactor;

        for (String key : electronicsPurchased.keySet()) {
            shoppingEmissions += electronicsPurchased.get(key) * electronicsEmissionFactor;
        }

        for (String key : otherPurchases.keySet()) {
            shoppingEmissions += otherPurchases.get(key) * otherPurchaseEmissionFactor;
        }
    }

    /**
     * Calculates the total CO2e emissions for the entry.
     * This includes transportation, meal, and shopping emissions.
     */
    public void calculateTotalEmissions() {
        calculateTransportationEmissions();
        calculateMealEmissions();
        calculateShoppingEmissions();
        totalCO2e = transportationEmissions + mealEmissions + shoppingEmissions;
    }

    // Getters

    /**
     * Gets the total CO2e emissions for transportation activities.
     *
     * @return Transportation CO2e emissions.
     */
    public float getTransportationEmissions() {
        return transportationEmissions;
    }

    /**
     * Gets the total CO2e emissions for meals.
     *
     * @return Meal CO2e emissions.
     */
    public float getMealEmissions() {
        return mealEmissions;
    }

    /**
     * Gets the total CO2e emissions for shopping activities.
     *
     * @return Shopping CO2e emissions.
     */
    public float getShoppingEmissions() {
        return shoppingEmissions;
    }

    /**
     * Gets the total CO2e emissions for this entry.
     *
     * @return Total CO2e emissions.
     */
    public float getTotalCO2e() {
        return totalCO2e;
    }

    // ToString

    /**
     * Returns a string representation of the entry for debugging purposes.
     *
     * @return A string containing activity type and CO2e emissions.
     */
    @Override
    public String toString() {
        return "Entry{" +
                "activityType='" + activityType + '\'' +
                ", transportationEmissions=" + transportationEmissions +
                ", mealEmissions=" + mealEmissions +
                ", shoppingEmissions=" + shoppingEmissions +
                ", totalCO2e=" + totalCO2e +
                '}';
    }
}
