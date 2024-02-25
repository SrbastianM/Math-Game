package com.srbastian.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var btnPlus : Button
    lateinit var btnSubstract : Button
    lateinit var btnMultiplication : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnPlus = findViewById(R.id.btnPlusQuiz)
        btnSubstract = findViewById(R.id.btnSubstrationQuiz)
        btnMultiplication = findViewById(R.id.btnMultiplicationQuiz)

        btnPlus.setOnClickListener {
            val intent = Intent(this@MainActivity, PlusActivity::class.java)
            startActivity(intent)
        }
        btnSubstract.setOnClickListener {
            val intent = Intent(this@MainActivity, SubtractionActivity::class.java)
            startActivity(intent)
        }
    }
}