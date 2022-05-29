package com.example.tipper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipper.R
import android.content.Intent
import android.widget.Button
import com.example.tipper.MainActivity
import com.example.tipper.QuizActivity

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        val calculateBMIButton = findViewById<Button>(R.id.calculate_button)
        val quizButton = findViewById<Button>(R.id.quiz_button)
        calculateBMIButton.setOnClickListener {
            startActivity(
                Intent(
                    this@HomeScreen,
                    MainActivity::class.java
                )
            )
        }
        quizButton.setOnClickListener {
            startActivity(
                Intent(
                    this@HomeScreen,
                    QuizActivity::class.java
                )
            )
        }
    }
}