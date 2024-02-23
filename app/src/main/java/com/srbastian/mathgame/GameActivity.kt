package com.srbastian.mathgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    lateinit var textLife : TextView
    lateinit var textScore : TextView
    lateinit var textTime : TextView
    lateinit var textQuestion : TextView

    lateinit var editTextAnswer : EditText

    lateinit var buttonCheck : Button
    lateinit var buttonNext : Button

    var correctAnswer = 0
    var userScore = 0
    var userLives = 3

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        textLife = findViewById(R.id.tv_lives_remaining)
        textScore = findViewById(R.id.tv_score_reached)
        textTime = findViewById(R.id.tv_time_left)
        editTextAnswer = findViewById(R.id.et_answer)
        textQuestion = findViewById(R.id.tv_question)
        buttonCheck = findViewById(R.id.btn_check)
        buttonNext = findViewById(R.id.btn_next)

        gameContinue()

        buttonCheck.setOnClickListener {
            val input = editTextAnswer.text.toString()
            // Check the input value aren't empty and if is it show a toast message
            if (input == "")
            {
                Toast.makeText(applicationContext, "Please write an answer or clic the next button", Toast.LENGTH_LONG)
                    .show()
            }
            else
            {
                val userAnswer = input.toInt()
                if (userAnswer == correctAnswer)
                {
                    userScore += 20
                    textScore.text = userScore.toString()
                    textQuestion.text = "WoW! Is correct!"
                    buttonNext.isEnabled = true
                }
                else
                {
                    userLives--
                    textQuestion.text = "Oh!, even close try again"
                    textLife.text = userLives.toString()
                    buttonNext.isEnabled = true
                }
            }
        }

        buttonNext.setOnClickListener {
            gameContinue()
            editTextAnswer.setText("")
        }
        }
        // Take 2 random numbers and set the global variable with the sum of those
        fun gameContinue() {
            val firstNumber = Random.nextInt(0, 100)
            val secondNumber = Random.nextInt(0, 100)
            textQuestion.text = "$firstNumber + $secondNumber"
            correctAnswer = firstNumber + secondNumber
        }
}