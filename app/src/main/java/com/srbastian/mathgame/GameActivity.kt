package com.srbastian.mathgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class GameActivity : AppCompatActivity() {
    lateinit var textLife : TextView
    lateinit var textScore : TextView
    lateinit var textTime : TextView
    lateinit var textQuestion : TextView

    lateinit var editTextAnswer : EditText

    lateinit var buttonCheck : Button
    lateinit var buttonNext : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        textLife = findViewById(R.id.tv_score_reached)
        textLife = findViewById(R.id.tv_lives_remaining)
        textLife = findViewById(R.id.tv_time_left)
        buttonCheck = findViewById(R.id.btn_check)
        buttonNext = findViewById(R.id.btn_next)
    }
}