package com.example.B07Planetze;

import java.util.ArrayList;

import java.util.ArrayList;

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

    // Getters and Setters

    /**
     * Gets the full name of the user.
     *
     * @return Full name of the user
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name of the user.
     *
     * @param fullName Full name to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets the unique identifier of the user.
     *
     * @return User ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param userId User ID to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the password of the user.
     *
     * @return User's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password Password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the email address of the user.
     *
     * @return User's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email Email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the calendar of activities for the user.
     *
     * @return Calendar of activities
     */
    public ArrayList<Date> getCalendar() {
        return calendar;
    }

    /**
     * Sets the calendar of activities for the user.
     *
     * @param calendar Calendar of activities to set
     */
    public void setCalendar(ArrayList<Date> calendar) {
        this.calendar = calendar;
    }

    /**
     * Adds a Date to the user's calendar.
     *
     * @param date Date to add
     */
    public void addToCalendar(Date date) {
        this.calendar.add(date);
    }

    /**
     * Removes a Date from the user's calendar.
     *
     * @param date Date to remove
     * @return True if the date was removed, false otherwise
     */
    public boolean removeFromCalendar(Date date) {
        return this.calendar.remove(date);
    }
}
