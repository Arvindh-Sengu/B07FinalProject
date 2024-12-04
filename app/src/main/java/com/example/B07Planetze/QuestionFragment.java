package com.example.B07Planetze;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.B07Planetze.R;

public class QuestionFragment extends Fragment {

    private TextView questionText;
    private RadioGroup answerGroup;
    private Button nextButton;

    private Question question;  // The Question object passed from the activity
    private int currentQuestionIndex;  // Track the current question index

    // Static factory method to create a new instance of QuestionFragment
    public static QuestionFragment newInstance(Question question, int currentQuestionIndex) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putSerializable("QUESTION", question); // Passing the Question object to the fragment
        args.putInt("INDEX", currentQuestionIndex);  // Passing the current question index
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question = (Question) getArguments().getSerializable("QUESTION"); // Retrieve the Question object
            currentQuestionIndex = getArguments().getInt("INDEX");  // Retrieve the current question index
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_fragment, container, false);

        questionText = view.findViewById(R.id.questionText);
        answerGroup = view.findViewById(R.id.answerGroup);
        nextButton = view.findViewById(R.id.nextButton);

        // Set up the question and options
        if (question != null) {
            questionText.setText(question.getText()); // Set the question text

            // Add the options to the RadioGroup
            String[] options = question.getOptions();
            if (options != null) {
                for (String option : options) {
                    RadioButton radioButton = new RadioButton(getActivity());

                    //The csv has text as 5 Occupants but need to add or more and i don't wanna change the large data set
                    if (option.equals("5 Occupants")){
                        radioButton.setText(option + " or more");
                    }
                    else {
                        radioButton.setText(option);
                    }
                    radioButton.setId(View.generateViewId());
                    answerGroup.addView(radioButton);
                }
            }
        }

        // When the "Next" button is clicked, update the answer in the Question object and load the next question
        nextButton.setOnClickListener(v -> {
            String answer = getSelectedAnswer();
            if (answer != null && !answer.isEmpty() && question != null) {
                //The csv has text as 5 Occupants but need to add or more and i don't wanna change the large data set
                if (answer.equals("5 Occupants or more")) {
                    question.setAnswer("5 Occupants");
                } else {
                    question.setAnswer(answer);  // Set the selected answer in the Question object
                }

                // Optionally, notify the activity to load the next question
                if (getActivity() instanceof AnnualCarbonFootprintQuestionsActivity) {
                    ((AnnualCarbonFootprintQuestionsActivity) getActivity()).loadNextQuestion(currentQuestionIndex);
                }
            }
            else {
            // Show a Toast if no answer is selected
            Toast.makeText(getActivity(), "Please select an option to proceed.", Toast.LENGTH_SHORT).show();
            }

        });

        return view;
    }

    // Get the selected answer from the RadioGroup
    private String getSelectedAnswer() {
        int selectedId = answerGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton selectedRadioButton = answerGroup.findViewById(selectedId);
            return selectedRadioButton != null ? selectedRadioButton.getText().toString() : null;
        }
        return null;
    }
}


