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
    private lateinit var textTimeLeftSubtraction: TextView
    lateinit var textQuestionSubtract: TextView

    private lateinit var editTextSubstractAnswer: EditText

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
        setContentView(R.layout.activity_subtraction)
        textLifeSubtraction = findViewById(R.id.tv_lives_subtraction_remaining)
        textScoreSubtraction = findViewById(R.id.tv_score_subtraction_reached)
        textTimeLeftSubtraction = findViewById(R.id.tv_time_left_subtraction)
        textQuestionSubtract = findViewById(R.id.tv_subtraction_question)
        editTextSubstractAnswer = findViewById(R.id.et_subtraction_answer)

        buttonCheckSubtraction = findViewById(R.id.btn_check_subtraction)
        buttonNextSubtraction = findViewById(R.id.btn_next_subtraction)

        gameContinue()

        buttonCheckSubtraction.setOnClickListener {
            val input = editTextSubstractAnswer.text.toString()
            if (input == "") {
                Toast.makeText(
                    applicationContext,
                    "Please write an answer or clic the next button",
                    Toast.LENGTH_LONG
                )
                    .show()
            } else {
                val userAnswer = input.toInt()
                if (userAnswer == correctAnswer) {
                    userScore += 20
                    textScoreSubtraction.text = userScore.toString()
                    textQuestionSubtract.text = "WoW! Is correct!"

                } else {

                    pauseTimer()

                    userLives -= 1
                    textLifeSubtraction.text = userLives.toString()
                    textQuestionSubtract.text = "Oh!, even close try again"
                    buttonNextSubtraction.isEnabled = true

                }
            }
        }

        buttonNextSubtraction.setOnClickListener {
            gameContinue()
            editTextSubstractAnswer.setText("")
            if (userLives == 0) {
                val intent = Intent(this@SubtractionActivity, GameOverActivity::class.java)
                intent.putExtra("score", userScore)
                startActivity(intent)
                finish()
            } else {
                gameContinue()
            }
        }
    }

    private fun gameContinue() {
        val randomNumber1 = Random.nextInt(0, 100)
        val randomNumber2 = Random.nextInt(0, 100)

        correctAnswer =
            if (randomNumber1 >= randomNumber2) {
                Pair(randomNumber1, randomNumber2)
                textQuestionSubtract.text = "$randomNumber1 - $randomNumber2"
                randomNumber1 - randomNumber2
            } else {
                Pair(randomNumber2, randomNumber1)
                textQuestionSubtract.text = "$randomNumber2 - $randomNumber1"
                randomNumber2 - randomNumber1
            }
        startTimer()
    }

    fun startTimer() {
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
                textLifeSubtraction.text = userLives.toString()
                textQuestionSubtract.text = "Oh! Time's up"
                if (userLives == 0) {
                    val intent = Intent(this@SubtractionActivity, GameOverActivity::class.java)
                    intent.putExtra("score", userScore)
                    startActivity(intent)
                    finish()
                } else {
                    gameContinue()
                }
            }
        }.start()

    }

    private fun resetTimer() {
        timeLeftInMillisub = starTimerInMillisub
        updateText()
    }

    private fun pauseTimer() {
        timer.cancel()
    }

    private fun updateText() {
        val remainingTime: Int = (timeLeftInMillisub / 1000).toInt()
        textTimeLeftSubtraction.text = String.format(Locale.getDefault(), "%2d", remainingTime)
    }
}
