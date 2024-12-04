package com.example.B07Planetze;

import com.example.B07Planetze.Date;

import java.util.ArrayList;

/**
 * Represents a user in the system.
 * Each user has a name, a unique ID, a password, an email, and a calendar of activities.
 */
public class User {
    private String fullName;
    private String userId;
    private String password;
    private String email;
    private ArrayList<Date> calendar;

    /**
     * Constructs a new User with the specified details.
     *
     * @param fullName Full name of the user
     * @param userId Unique identifier for the user
     * @param password User's password
     * @param email User's email address
     */
    public User(String fullName, String userId, String password, String email) {
        this.fullName = fullName;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.calendar = new ArrayList<>();
    }

    // Getters and setters for each field with JavaDoc
    /**
     * Gets the full name of the user.
     *
     * @return Full name of the user
     */
    public String getFullName() {
        return fullName;
    }

    // Add similar JavaDocs for other getters and setters...
}
