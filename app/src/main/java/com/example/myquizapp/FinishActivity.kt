package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity

class FinishActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        val tvScore = findViewById<TextView>(R.id.tv_score)
        val tvCongratulations = findViewById<TextView>(R.id.tv_congratulations)
        val btnFinish = findViewById<Button>(R.id.btn_finish)
        val ivFinishImg = findViewById<ImageView>(R.id.iv_finish_img)

        val numCorrectAnswers = intent.getStringExtra(Constants.CORRECT_ANSWERS)
        val numQuestions = intent.getStringExtra(Constants.TOTAL_QUESTIONS)
        val userName = intent.getStringExtra(Constants.USER_NAME)

        tvScore.text = "You got $numCorrectAnswers out of $numQuestions!"
        tvCongratulations.text = "Congratulations $userName!!"
        ivFinishImg.setImageResource(R.drawable.ic_trophy)

        btnFinish.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}