package com.example.tipper;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private List<Question> questionList;
    private TextView questionsTextView;
    private Button buttonA;
    private Button buttonB;
    private Button buttonC;
    private Button buttonD;
    private int currentQuestion;
    private int score;
    private int maxPoints;

    @Override
    protected void onCreate(Bundle savedInstanceData) {
        super.onCreate(savedInstanceData);
        setContentView(R.layout.activity_quiz);
        questionList = new ArrayList<>();
        setUpQuestions();
        maxPoints = questionList.size();
        score = 0;
        currentQuestion = 0;

        questionsTextView = findViewById(R.id.questionTextView);
        buttonA = findViewById(R.id.answer_A_button);
        buttonB = findViewById(R.id.answer_B_button);
        buttonC = findViewById(R.id.answer_C_button);
        buttonD = findViewById(R.id.answer_D_button);
        if (currentQuestion == 0) {
            questionsTextView.setText(questionList.get(0).getQuestion());
            buttonA.setText(questionList.get(0).getAnswers().get(0));
            buttonB.setText(questionList.get(0).getAnswers().get(1));
            buttonC.setText(questionList.get(0).getAnswers().get(2));
            buttonD.setText(questionList.get(0).getAnswers().get(3));
        }

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(buttonA.getText().equals(questionList.get(currentQuestion).getCorrectAnswer())){
                    score ++;
                }
                prepareNextQuestion();
            }
        });
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(buttonB.getText().equals(questionList.get(currentQuestion).getCorrectAnswer())){
                    score ++;
                }
                prepareNextQuestion();
            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(buttonC.getText().equals(questionList.get(currentQuestion).getCorrectAnswer())){
                    score ++;
                }
                prepareNextQuestion();
            }
        });
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(buttonD.getText().equals(questionList.get(currentQuestion).getCorrectAnswer())){
                    score ++;
                }
                prepareNextQuestion();
            }
        });
    }

    private void prepareNextQuestion(){
        currentQuestion ++;
        System.out.println(currentQuestion);
        if(currentQuestion < questionList.size()){
            questionsTextView.setText(questionList.get(currentQuestion).getQuestion());
            buttonA.setText(questionList.get(currentQuestion).getAnswers().get(0));
            buttonB.setText(questionList.get(currentQuestion).getAnswers().get(1));
            buttonC.setText(questionList.get(currentQuestion).getAnswers().get(2));
            buttonD.setText(questionList.get(currentQuestion).getAnswers().get(3));
        } else {
            buttonA.setVisibility(View.INVISIBLE);
            buttonB.setVisibility(View.INVISIBLE);
            buttonC.setVisibility(View.INVISIBLE);
            buttonD.setVisibility(View.INVISIBLE);
            questionsTextView.setText(String.format("You've earned %d out of %d points!", score, maxPoints));
        }
    }

    private void setUpQuestions() {
        Question firstQuestion = new Question("McChicken",
                "Which of these is not healthy?");
        firstQuestion.addAnswer("Ham Sandwich");
        firstQuestion.addAnswer("Scrambled eggs");
        firstQuestion.addAnswer("Yoghurt");
        firstQuestion.addAnswer("McChicken");
        Question secondQuestion = new Question("20-25",
                "Which BMI range represents what every human should strive for?");
        secondQuestion.addAnswer("20-25");
        secondQuestion.addAnswer("15-19");
        secondQuestion.addAnswer("25-30");
        secondQuestion.addAnswer("30-35");
        Question thirdQuestion = new Question("2.5l",
                "How much water should you at least drink on a daily basis?");
        thirdQuestion.addAnswer("0.5l");
        thirdQuestion.addAnswer("2.5l");
        thirdQuestion.addAnswer("3l");
        thirdQuestion.addAnswer("1.5l");
        Question fourthQuestion = new Question("Cereal",
                "Which one of these breakfast will set you up for the day?");
        fourthQuestion.addAnswer("Ham sandwich");
        fourthQuestion.addAnswer("Protein bar");
        fourthQuestion.addAnswer("Cereal");
        fourthQuestion.addAnswer("Crisps");
        Question fifthQuestion = new Question("7-8",
                "What is the optimal amount of hours that you should sleep?");
        fifthQuestion.addAnswer("5-6");
        fifthQuestion.addAnswer("7-8");
        fifthQuestion.addAnswer("9-10");
        fifthQuestion.addAnswer("More than 10");
        Question sixthQuestion = new Question("Motivation",
                "What is the key to weight loss??");
        sixthQuestion.addAnswer("Exercising");
        sixthQuestion.addAnswer("Frequent meals");
        sixthQuestion.addAnswer("Motivation");
        sixthQuestion.addAnswer("Drinking water");
        questionList.add(firstQuestion);
        questionList.add(secondQuestion);
        questionList.add(thirdQuestion);
        questionList.add(fourthQuestion);
        questionList.add(fifthQuestion);
        questionList.add(sixthQuestion);
    }


}
