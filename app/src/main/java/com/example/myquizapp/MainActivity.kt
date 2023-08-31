package com.example.myquizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myquizapp.ui.theme.MyQuizAppTheme

class MainActivity : ComponentActivity() {

    var userName: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart: Button = findViewById(R.id.btn_start)
        val tiName: EditText = findViewById(R.id.ti_name)

        btnStart.setOnClickListener {
            if(tiName.text.isEmpty()) {
                Toast.makeText(this,"Please enter your name", Toast.LENGTH_SHORT).show()
            } else {
                userName = tiName.text.toString()
                // Move to the other activity
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, tiName.text.toString())
                startActivity(intent)
//                finish() // Have to finish to close the previous activity. If not it will appear as two seperate versions of the app being open.
            }
        }
    }
}