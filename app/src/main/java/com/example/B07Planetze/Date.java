package com.example.B07Planetze;

import java.util.ArrayList;

public class Date {
    private ArrayList<Entry> entries; // List of all entries for the date

    // Constructor
    public Date() {
        this.entries = new ArrayList<>();
    }

    // Getters and Setters
    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    // Add an entry to the list
    public void addEntry(Entry entry) {
        this.entries.add(entry);
    }

    // ToString for debugging
    @Override
    public String toString() {
        return "Date{" +
                "entries=" + entries +
                '}';
    }
}

