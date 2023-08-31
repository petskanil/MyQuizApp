package com.example.myquizapp

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : ComponentActivity(), View.OnClickListener { // App seems to crash when using AppCompatActivity...

    private var mUserName: String? = null

    private var mCurrentPosition: Int = 0
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mNumberOfQuestions: Int = 0

    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null
    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null

    private var btnSubmit: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        ivImage = findViewById(R.id.iv_image)
        progressBar = findViewById(R.id.progress_bar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)

        btnSubmit = findViewById(R.id.btn_submit)

        tvOptionOne?.setOnClickListener(this) // Using the onclick method with itself as parameter
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        mQuestionsList = Constants.getQuestions()
        mQuestionsList?.shuffle()

        mNumberOfQuestions = mQuestionsList!!.size

        setQuestion()

    }

    private fun setQuestion() {

        val question: Question = mQuestionsList!![mCurrentPosition]

        ivImage?.setImageResource(question.image)
        progressBar?.progress = mCurrentPosition + 1
        tvProgress?.text = "${mCurrentPosition + 1}/${progressBar?.max}"
        tvQuestion?.text = question.question
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour

        if (mCurrentPosition == mQuestionsList!!.size) {
            btnSubmit?.text = "Finish"
        } else {
            btnSubmit?.text = "Submit"
        }
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        tvOptionOne?.let { options.add(0,it) }
        tvOptionTwo?.let { options.add(1,it) }
        tvOptionThree?.let { options.add(2,it) }
        tvOptionFour?.let { options.add(3,it) }

        for (option in options) {
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,R.drawable.input_background)
        }

    }

    private fun selectedOptionView(tv:TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.background = ContextCompat.getDrawable(this,R.drawable.selected_option)
        tv.setTypeface(tv.typeface,Typeface.BOLD)
    }

    private fun answerView(answer: Int, drawableView:Int) {
        when(answer){
            1-> {
                tvOptionOne?.background = ContextCompat.getDrawable(this,drawableView)
            }
            2-> {
                tvOptionTwo?.background = ContextCompat.getDrawable(this,drawableView)
            }
            3-> {
                tvOptionThree?.background = ContextCompat.getDrawable(this,drawableView)
            }
            4-> {
                tvOptionFour?.background = ContextCompat.getDrawable(this,drawableView)
            }
        }
    }

    override fun onClick(view: View?) {

        when(view?.id){
            R.id.tv_option_one -> {
                tvOptionOne?.let {
                    selectedOptionView(it,1)

                }
            }
            R.id.tv_option_two -> {
                tvOptionTwo?.let {
                    selectedOptionView(it,2)

                }
            }
            R.id.tv_option_three -> {
                tvOptionThree?.let {
                    selectedOptionView(it,3)

                }
            }
            R.id.tv_option_four -> {
                tvOptionFour?.let {
                    selectedOptionView(it,4)

                }
            }
            R.id.btn_submit -> {
                if(mSelectedOptionPosition == -1) {

                    // An answer has already been given
                    mCurrentPosition++
                    mSelectedOptionPosition = 0
                    defaultOptionsView()
                    setQuestion()


                }else if (mSelectedOptionPosition == -2) {
                    // Move to the finish activity
                    val intent = Intent(this, FinishActivity::class.java)
                    intent.putExtra(Constants.USER_NAME,mUserName)
                    intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers.toString())
                    intent.putExtra(Constants.TOTAL_QUESTIONS, mNumberOfQuestions.toString())
                    startActivity(intent)
                } else if(mSelectedOptionPosition == 0){
                    Toast.makeText(this,"Select an option",Toast.LENGTH_SHORT).show()
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition)
                    if(mSelectedOptionPosition == question?.correctAnswer) {
                        answerView(mSelectedOptionPosition,R.drawable.correct_answer)
                        mCorrectAnswers++
                    } else {
                        answerView(mSelectedOptionPosition,R.drawable.wrong_answer)
                        answerView(question!!.correctAnswer,R.drawable.correct_answer)
                    }
                    if(mCurrentPosition < mQuestionsList!!.size-1){
                        btnSubmit?.text = "Next question"
                        mSelectedOptionPosition = -1 // Next question
                    } else {
                        btnSubmit?.text = "Finish"
                        mSelectedOptionPosition = -2 // Finish
                    }


                }

            }
        }
    }


}