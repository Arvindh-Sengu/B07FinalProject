package com.example.B07Planetze;

import java.util.ArrayList;

public class User {
    private String fullName;
    private String userId;
    private String password;
    private String email;
    private ArrayList<Date> calendar;

    public User() {
        this.calendar = new ArrayList<>();
    }

    // Getters and Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Date> getCalendar() {
        return calendar;
    }

    public void setCalendar(ArrayList<Date> calendar) {
        this.calendar = calendar;
    }
}

