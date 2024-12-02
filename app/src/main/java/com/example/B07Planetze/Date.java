package com.example.B07Planetze;

import java.time.LocalDate;
import java.util.ArrayList;

public class Date {
    private LocalDate date; 
    private ArrayList<Entry> entries; 
    
    // Constructor
    public Date(LocalDate date) {
        this.date = date;
        this.entries = new ArrayList<>();
    }

    // Getter for date
    public LocalDate getDate() {
        return date;
    }

    // Setter for date
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    // Add an user entry to the list
    public void addEntry(Entry entry) {
        this.entries.add(entry);
    }

    // ToString shows date and date's entries
    @Override
    public String toString() {
        return "Date{" +
                "date=" + date +
                ", entries=" + entries +
                '}';
    }
}
