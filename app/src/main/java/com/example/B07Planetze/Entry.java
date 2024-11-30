package com.example.B07Planetze;

import java.util.HashMap;

public class Entry {
    private String activityType; // Type of activity (e.g., "Drive Personal Vehicle", "Meal", "Buy Electronics")
    private float totalCO2e; // Total CO2e for the activity
    private float distanceDriven; // Distance driven for transportation
    private HashMap<String, Float> transitDistances; // Bus, train, subway distances
    private float distanceCycledOrWalked; // Distance cycled or walked
    private HashMap<String, Integer> flightCounts; // Number of short-haul and long-haul flights
    private HashMap<String, Integer> foodServings; // Servings of beef, pork, chicken, fish, plant-based
    private int numberOfClothingBought; // Number of clothing items purchased
    private HashMap<String, Integer> electronicsPurchased; // Electronics purchases
    private HashMap<String, Integer> otherPurchases; // Other purchases (e.g., furniture, appliances)
    private HashMap<String, Float> energyBills; // Energy bills (electricity, gas, water)

    // Constructor
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

    // Getters and Setters
    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public float getTotalCO2e() {
        return totalCO2e;
    }

    public void setTotalCO2e(float totalCO2e) {
        this.totalCO2e = totalCO2e;
    }

    public float getDistanceDriven() {
        return distanceDriven;
    }

    public void setDistanceDriven(float distanceDriven) {
        this.distanceDriven = distanceDriven;
    }

    public HashMap<String, Float> getTransitDistances() {
        return transitDistances;
    }

    public void setTransitDistances(HashMap<String, Float> transitDistances) {
        this.transitDistances = transitDistances;
    }

    public float getDistanceCycledOrWalked() {
        return distanceCycledOrWalked;
    }

    public void setDistanceCycledOrWalked(float distanceCycledOrWalked) {
        this.distanceCycledOrWalked = distanceCycledOrWalked;
    }

    public HashMap<String, Integer> getFlightCounts() {
        return flightCounts;
    }

    public void setFlightCounts(HashMap<String, Integer> flightCounts) {
        this.flightCounts = flightCounts;
    }

    public HashMap<String, Integer> getFoodServings() {
        return foodServings;
    }

    public void setFoodServings(HashMap<String, Integer> foodServings) {
        this.foodServings = foodServings;
    }

    public int getNumberOfClothingBought() {
        return numberOfClothingBought;
    }

    public void setNumberOfClothingBought(int numberOfClothingBought) {
        this.numberOfClothingBought = numberOfClothingBought;
    }

    public HashMap<String, Integer> getElectronicsPurchased() {
        return electronicsPurchased;
    }

    public void setElectronicsPurchased(HashMap<String, Integer> electronicsPurchased) {
        this.electronicsPurchased = electronicsPurchased;
    }

    public HashMap<String, Integer> getOtherPurchases() {
        return otherPurchases;
    }

    public void setOtherPurchases(HashMap<String, Integer> otherPurchases) {
        this.otherPurchases = otherPurchases;
    }

    public HashMap<String, Float> getEnergyBills() {
        return energyBills;
    }

    public void setEnergyBills(HashMap<String, Float> energyBills) {
        this.energyBills = energyBills;
    }

    // ToString for debugging
    @Override
    public String toString() {
        return "Entry{" +
                "activityType='" + activityType + '\'' +
                ", totalCO2e=" + totalCO2e +
                ", distanceDriven=" + distanceDriven +
                ", transitDistances=" + transitDistances +
                ", distanceCycledOrWalked=" + distanceCycledOrWalked +
                ", flightCounts=" + flightCounts +
                ", foodServings=" + foodServings +
                ", numberOfClothingBought=" + numberOfClothingBought +
                ", electronicsPurchased=" + electronicsPurchased +
                ", otherPurchases=" + otherPurchases +
                ", energyBills=" + energyBills +
                '}';
    }
}

