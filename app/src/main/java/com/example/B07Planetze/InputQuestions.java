package com.example.B07Planetze;

import java.util.ArrayList;
import java.util.List;

public class InputQuestions {

    // This method returns the list of all questions for the survey
    public static List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        // Personal Vehicle Use
        questions.add(new Question("Do you own or regularly use a car?", new String[]{"Yes", "No"}));
        questions.add(new Question("What type of car do you drive?", new String[]{"Gasoline", "Diesel", "Hybrid", "Electric", "I don’t know"}));
        questions.add(new Question("How many kilometers/miles do you drive per year?", new String[]{"Up to 5,000 km (3,000 miles)", "5,000–10,000 km (3,000–6,000 miles)", "10,000–15,000 km (6,000–9,000 miles)", "15,000–20,000 km (9,000–12,000 miles)", "20,000–25,000 km (12,000–15,000 miles)", "More than 25,000 km (15,000 miles)"}));

        // Public Transportation
        questions.add(new Question("How often do you use public transportation (bus, train, subway)?", new String[]{"Never", "Occasionally (1-2 times/week)", "Frequently (3-4 times/week)", "Always (5+ times/week)"}));
        questions.add(new Question("How much time do you spend on public transport per week (bus, train, subway)?", new String[]{"Under 1 hour", "1-3 hours", "3-5 hours", "5-10 hours", "More than 10 hours"}));

        // Air Travel
        questions.add(new Question("How many short-haul flights (less than 1,500 km / 932 miles) have you taken in the past year?", new String[]{"None", "1-2 flights", "3-5 flights", "6-10 flights", "More than 10 flights"}));
        questions.add(new Question("How many long-haul flights (more than 1,500 km / 932 miles) have you taken in the past year?", new String[]{"None", "1-2 flights", "3-5 flights", "6-10 flights", "More than 10 flights"}));

        // Food
        questions.add(new Question("What best describes your diet?", new String[]{"Vegetarian", "Vegan", "Pescatarian (fish/seafood)", "Meat-based (eat all types of animal products)"}));

        // Separate Questions for Meat Consumption (appear only if Meat-based diet is selected)
        questions.add(new Question("How often do you eat beef?", new String[]{"Daily", "Frequently (3-5 times/week)", "Occasionally (1-2 times/week)", "Never"}));
        questions.add(new Question("How often do you eat pork?", new String[]{"Daily", "Frequently (3-5 times/week)", "Occasionally (1-2 times/week)", "Never"}));
        questions.add(new Question("How often do you eat chicken?", new String[]{"Daily", "Frequently (3-5 times/week)", "Occasionally (1-2 times/week)", "Never"}));
        questions.add(new Question("How often do you eat fish/seafood?", new String[]{"Daily", "Frequently (3-5 times/week)", "Occasionally (1-2 times/week)", "Never"}));

        // Food Waste
        questions.add(new Question("How often do you waste food or throw away uneaten leftovers?", new String[]{"Never", "Rarely", "Occasionally", "Frequently"}));

        // Housing
        questions.add(new Question("What type of home do you live in?", new String[]{"Detached house", "Semi-detached house", "Townhouse", "Condo/Apartment", "Other"}));
        questions.add(new Question("How many people live in your household?", new String[]{"1", "2", "3-4", "5 or more"}));
        questions.add(new Question("What is the size of your home?", new String[]{"Under 1000 sq. ft.", "1000-2000 sq. ft.", "Over 2000 sq. ft."}));
        questions.add(new Question("What type of energy do you use to heat your home?", new String[]{"Natural Gas", "Electricity", "Oil", "Propane", "Wood", "Other"}));
        questions.add(new Question("What is your average monthly electricity bill?", new String[]{"Under $50", "$50-$100", "$100-$150", "$150-$200", "Over $200"}));
        questions.add(new Question("What type of energy do you use to heat water in your home?", new String[]{"Natural Gas", "Electricity", "Oil", "Propane", "Solar", "Other"}));
        questions.add(new Question("Do you use any renewable energy sources for electricity or heating (e.g., solar, wind)?", new String[]{"Yes, primarily (more than 50% of energy use)", "Yes, partially (less than 50% of energy use)", "No"}));

        // Consumption
        questions.add(new Question("How often do you buy new clothes?", new String[]{"Monthly", "Quarterly", "Annually", "Rarely"}));
        questions.add(new Question("Do you buy second-hand or eco-friendly products?", new String[]{"Yes, regularly", "Yes, occasionally", "No"}));
        questions.add(new Question("How many electronic devices (phones, laptops, etc.) have you purchased in the past year?", new String[]{"None", "1", "2", "3 or more"}));
        questions.add(new Question("How often do you recycle?", new String[]{"Never", "Occasionally", "Frequently", "Always"}));

        return questions;
    }
}
