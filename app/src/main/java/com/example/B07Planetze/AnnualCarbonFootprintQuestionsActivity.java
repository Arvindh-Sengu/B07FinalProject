package com.example.B07Planetze;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.B07Planetze.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnnualCarbonFootprintQuestionsActivity extends AppCompatActivity {

    private int currentQuestionIndex = 0;
    private List<Question> questions;
    private String selectedCountry;
    private double countryEmission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.annual_carbon_footprint_questions);

        // Retrieve country and emission data from the intent
        selectedCountry = getIntent().getStringExtra("SELECTED_COUNTRY");
        countryEmission = getIntent().getDoubleExtra("COUNTRY_EMISSION", 0.0);

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

        Question currentQuestion = questions.get(currentQuestionIndex);
        //if user doesn't have car skip car related questions
        if (currentQuestion.getText().equals("Do you own or regularly use a car?") &&
                currentQuestion.getAnswer().equals("No")){
            this.currentQuestionIndex = currentQuestionIndex + 3;
        }
        //if user doesn't have a meat based diet skip skip other meat questions
        else if (currentQuestion.getText().equals("What best describes your diet?") &&
                !currentQuestion.getAnswer().equals("Meat-based (eat all types of animal products)")) {
            this.currentQuestionIndex = currentQuestionIndex + 5;
        }
        else {
            this.currentQuestionIndex = currentQuestionIndex + 1;
        }

        if (this.currentQuestionIndex < questions.size()) {
            loadQuestionFragment(questions.get(this.currentQuestionIndex), this.currentQuestionIndex);
        } else {
            // Survey completed
            onSurveyCompleted();
        }
    }

    public void onSurveyCompleted() {
        String[] answers = new String[questions.size()];
        for (int i = 0; i < questions.size(); i++) {
            answers[i] = questions.get(i).getAnswer();
        }

        CarbonFootprintCalculator calculator = new ExcelBasedCarbonFootprintCalculator(this, answers);

        double transportationEmissions = calculator.transportationEmissionCalculations();
        double foodEmissions = calculator.foodEmissionCalculations();
        double housingEmissions = calculator.housingEmissionCalculations();
        double consumptionEmissions = calculator.consumptionEmissionCalculations();

        Intent intent = new Intent(this, AnnualCarbonFootprintResultsActivity.class);
        intent.putExtra("TRANSPORTATION_EMISSIONS", transportationEmissions);
        intent.putExtra("FOOD_EMISSIONS", foodEmissions);
        intent.putExtra("HOUSING_EMISSIONS", housingEmissions);
        intent.putExtra("CONSUMPTION_EMISSIONS", consumptionEmissions);
        intent.putExtra("SELECTED_COUNTRY", selectedCountry);
        intent.putExtra("COUNTRY_EMISSION", countryEmission);

        startActivity(intent);
        finish();
    }

    // Helper method to get the answer from a specific question
    private String getAnswer(int index) {
        return questions.get(index).getAnswer();
    }
}


