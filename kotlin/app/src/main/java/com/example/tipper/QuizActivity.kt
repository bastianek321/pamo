package com.example.tipper

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import android.view.View
import android.widget.Button
import java.util.ArrayList

class QuizActivity : AppCompatActivity() {
    private var questionList: MutableList<Question>? = null
    private var questionsTextView: TextView? = null
    private var buttonA: Button? = null
    private var buttonB: Button? = null
    private var buttonC: Button? = null
    private var buttonD: Button? = null
    private var currentQuestion = 0
    private var score = 0
    private var maxPoints = 0
    override fun onCreate(savedInstanceData: Bundle?) {
        super.onCreate(savedInstanceData)
        setContentView(R.layout.activity_quiz)
        questionList = ArrayList()
        setUpQuestions()
        maxPoints = questionList?.size!!
        score = 0
        currentQuestion = 0
        questionsTextView = findViewById(R.id.questionTextView)
        buttonA = findViewById(R.id.answer_A_button)
        buttonB = findViewById(R.id.answer_B_button)
        buttonC = findViewById(R.id.answer_C_button)
        buttonD = findViewById(R.id.answer_D_button)
        if (currentQuestion == 0) {
            questionsTextView?.setText(questionList?.get(0)?.question)
            buttonA?.setText(questionList?.get(0)?.getAnswers()?.get(0))
            buttonB?.setText(questionList?.get(0)?.getAnswers()?.get(1))
            buttonC?.setText(questionList?.get(0)?.getAnswers()?.get(2))
            buttonD?.setText(questionList?.get(0)?.getAnswers()?.get(3))
        }
        buttonA?.setOnClickListener(View.OnClickListener {
            if (buttonA?.getText() == questionList?.get(currentQuestion)?.correctAnswer) {
                score++
            }
            prepareNextQuestion()
        })
        buttonB?.setOnClickListener(View.OnClickListener {
            if (buttonB?.getText() == questionList?.get(currentQuestion)?.correctAnswer) {
                score++
            }
            prepareNextQuestion()
        })
        buttonC?.setOnClickListener(View.OnClickListener {
            if (buttonC?.getText() == questionList?.get(currentQuestion)?.correctAnswer) {
                score++
            }
            prepareNextQuestion()
        })
        buttonD?.setOnClickListener(View.OnClickListener {
            if (buttonD?.getText() == questionList?.get(currentQuestion)?.correctAnswer) {
                score++
            }
            prepareNextQuestion()
        })
    }

    private fun prepareNextQuestion() {
        currentQuestion++
        println(currentQuestion)
        if (currentQuestion < questionList!!.size) {
            questionsTextView!!.text = questionList!![currentQuestion].question
            buttonA!!.text = questionList!![currentQuestion].getAnswers()[0]
            buttonB!!.text = questionList!![currentQuestion].getAnswers()[1]
            buttonC!!.text = questionList!![currentQuestion].getAnswers()[2]
            buttonD!!.text = questionList!![currentQuestion].getAnswers()[3]
        } else {
            buttonA!!.visibility = View.INVISIBLE
            buttonB!!.visibility = View.INVISIBLE
            buttonC!!.visibility = View.INVISIBLE
            buttonD!!.visibility = View.INVISIBLE
            questionsTextView!!.text =
                String.format("You've earned %d out of %d points!", score, maxPoints)
        }
    }

    private fun setUpQuestions() {
        val firstQuestion = Question(
            "McChicken",
            "Which of these is not healthy?"
        )
        firstQuestion.addAnswer("Ham Sandwich")
        firstQuestion.addAnswer("Scrambled eggs")
        firstQuestion.addAnswer("Yoghurt")
        firstQuestion.addAnswer("McChicken")
        val secondQuestion = Question(
            "20-25",
            "Which BMI range represents what every human should strive for?"
        )
        secondQuestion.addAnswer("20-25")
        secondQuestion.addAnswer("15-19")
        secondQuestion.addAnswer("25-30")
        secondQuestion.addAnswer("30-35")
        val thirdQuestion = Question(
            "2.5l",
            "How much water should you at least drink on a daily basis?"
        )
        thirdQuestion.addAnswer("0.5l")
        thirdQuestion.addAnswer("2.5l")
        thirdQuestion.addAnswer("3l")
        thirdQuestion.addAnswer("1.5l")
        val fourthQuestion = Question(
            "Cereal",
            "Which one of these breakfast will set you up for the day?"
        )
        fourthQuestion.addAnswer("Ham sandwich")
        fourthQuestion.addAnswer("Protein bar")
        fourthQuestion.addAnswer("Cereal")
        fourthQuestion.addAnswer("Crisps")
        val fifthQuestion = Question(
            "7-8",
            "What is the optimal amount of hours that you should sleep?"
        )
        fifthQuestion.addAnswer("5-6")
        fifthQuestion.addAnswer("7-8")
        fifthQuestion.addAnswer("9-10")
        fifthQuestion.addAnswer("More than 10")
        val sixthQuestion = Question(
            "Motivation",
            "What is the key to weight loss??"
        )
        sixthQuestion.addAnswer("Exercising")
        sixthQuestion.addAnswer("Frequent meals")
        sixthQuestion.addAnswer("Motivation")
        sixthQuestion.addAnswer("Drinking water")
        questionList!!.add(firstQuestion)
        questionList!!.add(secondQuestion)
        questionList!!.add(thirdQuestion)
        questionList!!.add(fourthQuestion)
        questionList!!.add(fifthQuestion)
        questionList!!.add(sixthQuestion)
    }
}