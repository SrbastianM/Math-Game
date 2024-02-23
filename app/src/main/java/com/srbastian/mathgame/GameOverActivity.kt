package com.srbastian.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class GameOverActivity : AppCompatActivity() {

    lateinit var score : TextView
    lateinit var playAgain : Button
    lateinit var exit : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        score = findViewById(R.id.tv_score_win)
        playAgain = findViewById(R.id.btnTryAgain)
        exit = findViewById(R.id.btnExit)

        val userScore = intent.getIntExtra("score", 0)
        score.text = "Your Score: $userScore"

        playAgain.setOnClickListener {
            val intent = Intent(this@GameOverActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        exit.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

    }
}