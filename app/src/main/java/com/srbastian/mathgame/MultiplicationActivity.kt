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

class MultiplicationActivity : AppCompatActivity() {
    lateinit var textLifeMultiplication: TextView
    lateinit var textScoreMultiplication: TextView
    lateinit var textTimeMultiplication: TextView
    lateinit var textQuestionMultiplication: TextView

    lateinit var editTextSubstractAnswer: EditText

    lateinit var buttonCheckSubtraction: Button
    lateinit var buttonNextSubtraction: Button

    var correctAnswer = 0
    var userScore = 0
    var userLives = 3

    lateinit var timer: CountDownTimer
    private val starTimerInMillisub: Long = 60000
    var timeLeftInMillisub: Long = starTimerInMillisub

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiplication)
        textLifeMultiplication = findViewById(R.id.tv_lives_multiplication_remaining)
        textScoreMultiplication = findViewById(R.id.tv_score_multiplication_reached)
        textTimeMultiplication = findViewById(R.id.tv_time_left_multiplication)
        textQuestionMultiplication = findViewById(R.id.tv_multiplication_question)

        editTextSubstractAnswer = findViewById(R.id.et_multiplication_answer)

        buttonCheckSubtraction = findViewById(R.id.btn_check_multiplication)
        buttonNextSubtraction = findViewById(R.id.btn_next_multiplication)

        gameContinue()

        buttonCheckSubtraction.setOnClickListener {
            val input = editTextSubstractAnswer.text.toString()
            if (input == "") {
                Toast.makeText(
                    applicationContext,
                    "Please write an answer or clic the next button",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val userAnswer = input.toInt()
                if (userAnswer == correctAnswer) {
                    userScore += 20
                    textScoreMultiplication.text = userScore.toString()
                    textQuestionMultiplication.text = "WoW! Is correct!\""
                } else {

                    pauseTimer()

                    userLives--
                    textLifeMultiplication.text = userLives.toString()
                    textQuestionMultiplication.text = "Oh!, even close try again"
                    buttonNextSubtraction.isEnabled = true
                }
            }
        }

        buttonNextSubtraction.setOnClickListener {
            gameContinue()
            editTextSubstractAnswer.setText("")
            if (userLives == 0) {
                val intent = Intent(this@MultiplicationActivity, GameOverActivity::class.java)
                intent.putExtra("score", userScore)
                startActivity(intent)
                finish()
            } else {
                gameContinue()
                updateText()
            }
        }

    }

    private fun gameContinue() {
        val randomNumber1 = Random.nextInt(0, 100)
        val randomNumber2 = Random.nextInt(0, 100)
        textQuestionMultiplication.text = "$randomNumber1 * $randomNumber2"
        correctAnswer = randomNumber1 * randomNumber2
        startTimer()
    }

    private fun startTimer() {
        timer = object : CountDownTimer(timeLeftInMillisub, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillisub = millisUntilFinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()
                userLives--
                textLifeMultiplication.text = userLives.toString()
                textQuestionMultiplication.text = "Oh! Time's up"

            }

        }.start()
    }

    private fun updateText() {
        val remainingTime = (timeLeftInMillisub / 1000).toInt()
        textTimeMultiplication.text = String.format(Locale.getDefault(), "%2d", remainingTime)
    }
    private fun resetTimer() {
        timeLeftInMillisub = starTimerInMillisub
        updateText()
    }

    private fun pauseTimer() {
        timer.cancel()
    }
}