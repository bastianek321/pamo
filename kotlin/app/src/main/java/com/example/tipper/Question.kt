package com.example.tipper

import java.util.ArrayList

class Question(correctAnswer: String, question: String) {
    var question: String
    private var answers: MutableList<String>
    var correctAnswer: String
    fun getAnswers(): List<String> {
        return answers
    }

    fun setAnswers(answers: MutableList<String>) {
        this.answers = answers
    }

    fun addAnswer(answer: String) {
        answers.add(answer)
    }

    init {
        answers = ArrayList()
        this.correctAnswer = correctAnswer
        this.question = question
    }
}