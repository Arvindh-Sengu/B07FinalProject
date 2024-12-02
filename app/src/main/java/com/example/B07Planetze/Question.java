package com.example.B07Planetze;

import java.io.Serializable;

public class Question implements Serializable {

    private String text;  // The question text
    private String[] options;  // The possible answers
    private String answer;  // The selected answer (set by the fragment)

    // Constructor
    public Question(String text, String[] options) {
        this.text = text;
        this.options = options;
        this.answer = "";
    }

    // Getters and setters
    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
