package com.example.B07Planetze;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.B07Planetze.R;
import java.util.ArrayList;
import java.util.List;

public class AnnualCarbonFootprintQuestionsActivity extends AppCompatActivity {

    private int currentQuestionIndex = 0;
    private List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.annual_carbon_footprint_questions);

        // Load the questions using InputQuestions class
        questions = InputQuestions.getQuestions();

        // Load the first question
        loadQuestionFragment(questions.get(currentQuestionIndex), currentQuestionIndex);
    }

    private void loadQuestionFragment(Question question, int index) {
        // Pass the question and the current index to the fragment
        QuestionFragment fragment = QuestionFragment.newInstance(question, index);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    public void loadNextQuestion(int currentQuestionIndex) {
        this.currentQuestionIndex = currentQuestionIndex + 1;

        if (this.currentQuestionIndex < questions.size()) {
            loadQuestionFragment(questions.get(this.currentQuestionIndex), this.currentQuestionIndex);
        } else {
            // Survey completed
            onSurveyCompleted();
        }
    }

    public void onSurveyCompleted() {
        double transportationEmissions = transportationEmissionCalculations();
        double foodEmissions = foodEmissionCalculations();
        System.out.println(foodEmissions);
        Toast.makeText(this, "Survey completed!", Toast.LENGTH_SHORT).show();
    }

    public double transportationEmissionCalculations(){
        TransportationCarbonFootprintCalculator calculator = new TransportationCarbonFootprintCalculator();
        String hasCar = getAnswer(0);
        String typeCar = getAnswer(1);
        String kmPerYear = getAnswer(2);
        String publicTransportationFrequency = getAnswer(3);
        String publicTransportationTime = getAnswer(4);
        String shortHaulFlights = getAnswer(5);
        String longHaulFlights = getAnswer(6);
        double transportationEmissions = calculator.calculateTransportationEmissions(hasCar
                , typeCar, kmPerYear,
                publicTransportationFrequency, publicTransportationTime,
                shortHaulFlights, longHaulFlights);
        return transportationEmissions;
    }

    public double foodEmissionCalculations(){
        FoodCarbonFootprintCalculator calculator = new FoodCarbonFootprintCalculator();
        String dietType = getAnswer(7);
        String beefConsumptionFrequency = getAnswer(8);
        String porkConsumptionFrequency  = getAnswer(9);
        String chickenConsumptionFrequency  = getAnswer(10);
        String fishConsumptionFrequency  = getAnswer(11);
        String foodWasteFrequency = getAnswer(12);

        double totalFoodEmissions = calculator.calculateTotalFoodEmissions(
                dietType,
                beefConsumptionFrequency, // Beef
                porkConsumptionFrequency, // Pork
                chickenConsumptionFrequency, // Chicken
                fishConsumptionFrequency, // Fish
                foodWasteFrequency // Food waste
        );
        return totalFoodEmissions;
    }

    // Helper method to get the answer from a specific question
    private String getAnswer(int index) {
        return questions.get(index).getAnswer();
    }
}


