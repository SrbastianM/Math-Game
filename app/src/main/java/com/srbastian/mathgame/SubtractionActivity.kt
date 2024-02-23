package com.srbastian.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Locale
import kotlin.random.Random

class SubtractionActivity : AppCompatActivity() {
    lateinit var textLifeSubtraction: TextView
    lateinit var textScoreSubtraction: TextView
    lateinit var textTimeSubtraction: TextView
    lateinit var textQuestionSubtract: TextView

    lateinit var editTextSubstractAnswer: EditText

    lateinit var buttonCheck: Button
    lateinit var buttonNext: Button

    var correctAnswer = 0
    var userScore = 0
    var userLives = 3

    lateinit var timer: CountDownTimer
    private val starTimerInMillis: Long = 60000
    var timeLeftInMillis: Long = starTimerInMillis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subtraction)

        textLifeSubtraction = findViewById(R.id.tv_lives_subtraction_remaining)
        textScoreSubtraction = findViewById(R.id.tv_score_subtraction_reached)
        textTimeSubtraction = findViewById(R.id.tv_time_left_subtraction)
        editTextSubstractAnswer = findViewById(R.id.et_subtraction_answer)
        textQuestionSubtract = findViewById(R.id.tv_subtraction_question)
        buttonCheck = findViewById(R.id.btn_check_subtraction)
        buttonNext = findViewById(R.id.btn_next_subtraction)

        buttonCheck.setOnClickListener {
            val input = editTextSubstractAnswer.text.toString()
            // Check the input value aren't empty and if is it show a toast message
            if (input == "") {
                Toast.makeText(
                    applicationContext,
                    "Please write an answer or clic the next button",
                    Toast.LENGTH_LONG
                )
                    .show()
            } else {

                pauseTimer()

                val userAnswer = input.toInt()
                if (userAnswer == correctAnswer) {
                    userScore += 20
                    textScoreSubtraction.text = userScore.toString()
                    textQuestionSubtract.text = "WoW! Is correct!"
                    buttonNext.isEnabled = true
                } else {
                    userLives--
                    textQuestionSubtract.text = "Oh!, even close try again"
                    textLifeSubtraction.text = userLives.toString()
                    buttonNext.isEnabled = true
                }
            }
        }

        buttonNext.setOnClickListener {
            pauseTimer()
            resetTimer()
            gameContinue()
            editTextSubstractAnswer.setText("")
            if (userLives == 0) {
                Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_LONG).show()
                val intent = Intent(this@SubtractionActivity, GameOverActivity::class.java)
                intent.putExtra("score", userScore)
                startActivity(intent)
                finish()
            } else {
                gameContinue()
            }
        }
    }

    // Take 2 random numbers and set the global variable with the sum of those
    fun gameContinue() {
        val firstNumber = Random.nextInt(0, 100)
        val secondNumber = Random.nextInt(0, 100)
        textQuestionSubtract.text = "$firstNumber - $secondNumber"
        correctAnswer = firstNumber - secondNumber
        startTimer()
    }

    fun startTimer() {
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()
                userLives--
                textLifeSubtraction.text = userLives.toString()
                textQuestionSubtract.text = "Oh! Time's up"
            }

        }.start()
    }

    fun updateText() {
        val remainingTime: Int = (timeLeftInMillis / 1000).toInt()
        textTimeSubtraction.text = String.format(Locale.getDefault(), "%2d", remainingTime)
    }

    fun pauseTimer() {
        timer.cancel()
    }

    fun resetTimer() {
        timeLeftInMillis = starTimerInMillis
        updateText()
    }

}
