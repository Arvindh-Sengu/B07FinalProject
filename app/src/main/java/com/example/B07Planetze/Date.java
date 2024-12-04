package com.example.B07Planetze;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a specific date and the associated user entries for that date.
 * Provides methods to manage and interact with the date and its entries,
 * including adding entries and calculating emissions based on the associated activities.
 */
public class Date {
    private LocalDate date; // The date being represented.
    private ArrayList<Entry> entries; // List of user entries for this date.
    private float transportationEmissions; // Total transportation emissions for the date
    private float mealEmissions; // Total meal emissions for the date
    private float shoppingEmissions; // Total shopping emissions for the date
    private float totalEmissions; // Total emissions for the date

    /**
     * Constructs a Date object with a specified LocalDate.
     * Initializes the list of entries and sets all emissions to 0.
     *
     * @param date The LocalDate to associate with this object.
     */
    public Date(LocalDate date) {
        this.date = date;
        this.entries = new ArrayList<>();
        this.transportationEmissions = 0f;
        this.mealEmissions = 0f;
        this.shoppingEmissions = 0f;
        this.totalEmissions = 0f;
    }

    /**
     * Gets the date associated with this object.
     *
     * @return The LocalDate of this Date object.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date for this object.
     *
     * @param date The LocalDate to set for this Date object.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the list of entries associated with this date.
     *
     * @return An ArrayList of Entry objects for this date.
     */
    public ArrayList<Entry> getEntries() {
        return entries;
    }

    /**
     * Sets the list of entries for this date.
     *
     * @param entries An ArrayList of Entry objects to associate with this date.
     */
    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    /**
     * Adds a new entry to the date and recalculates the aggregate emissions.
     *
     * @param entry The Entry object to add.
     */
    public void addEntry(Entry entry) {
        entries.add(entry);
        recalculateEmissions(); // Update the aggregate emissions whenever a new entry is added
    }

    /**
     * Recalculates the aggregate emissions based on all entries for this date.
     * Updates the values of transportation, meal, shopping, and total emissions.
     */
    private void recalculateEmissions() {
        transportationEmissions = 0f;
        mealEmissions = 0f;
        shoppingEmissions = 0f;
        totalEmissions = 0f;

        for (Entry entry : entries) {
            entry.calculateTotalEmissions(); // Ensure each entry's emissions are calculated
            transportationEmissions += entry.getTransportationEmissions();
            mealEmissions += entry.getMealEmissions();
            shoppingEmissions += entry.getShoppingEmissions();
        }

        totalEmissions = transportationEmissions + mealEmissions + shoppingEmissions;
    }

    /**
     * Gets the total transportation emissions for this date.
     *
     * @return The transportation emissions in kg CO2e.
     */
    public float getTransportationEmissions() {
        return transportationEmissions;
    }

    /**
     * Gets the total meal emissions for this date.
     *
     * @return The meal emissions in kg CO2e.
     */
    public float getMealEmissions() {
        return mealEmissions;
    }

    /**
     * Gets the total shopping emissions for this date.
     *
     * @return The shopping emissions in kg CO2e.
     */
    public float getShoppingEmissions() {
        return shoppingEmissions;
    }

    /**
     * Gets the total emissions for this date.
     *
     * @return The total emissions in kg CO2e.
     */
    public float getTotalEmissions() {
        return totalEmissions;
    }

    /**
     * Returns a string representation of the Date object, including the date
     * and the associated entries.
     *
     * @return A string representation of this Date object, with the date and entries.
     */
    @Override
    public String toString() {
        return "Date{" +
                "date=" + date +
                ", entries=" + entries +
                '}';
    }
}
