package com.example.tipper;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String question;
    private List<String> answers;
    private String correctAnswer;

    public Question(String correctAnswer, String question) {
        this.answers = new ArrayList<>();
        this.correctAnswer = correctAnswer;
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void addAnswer(String answer){
        this.answers.add(answer);
    }
}
