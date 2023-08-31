package com.example.myquizapp

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"
    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        questionsList.add(
            Question(
                1,
                "What country does this flag belong to?",
                R.drawable.ic_flag_of_argentina,
                "Japan", "Canada", "Argentina", "Italy",
                3
            )
        )
        questionsList.add(
            Question(
                2,
                "What country does this flag belong to?",
                R.drawable.ic_flag_of_australia,
                "France", "Mexico", "Australia", "Spain",
                3
            )
        )
        questionsList.add(
            Question(
                3,
                "What country does this flag belong to?",
                R.drawable.ic_flag_of_belgium,
                "Russia", "Egypt", "China", "Belgium",
                4
            )
        )
        questionsList.add(
            Question(
                4,
                "What country does this flag belong to?",
                R.drawable.ic_flag_of_brazil,
                "Brazil", "Sweden", "Norway", "Columbia",
                1
            )
        )
        questionsList.add(
            Question(
                5,
                "What country does this flag belong to?",
                R.drawable.ic_flag_of_denmark,
                "Turkey", "Indonesia", "Greece", "Denmark",
                4
            )
        )
        questionsList.add(
            Question(
                6,
                "What country does this flag belong to?",
                R.drawable.ic_flag_of_fiji,
                "Fiji", "Vietnam", "Pakistan", "Poland",
                1
            )
        )
        questionsList.add(
            Question(
                7,
                "What country does this flag belong to?",
                R.drawable.ic_flag_of_germany,
                "Brazil", "Germany", "Netherlands", "Chile",
                2
            )
        )
        questionsList.add(
            Question(
                8,
                "What country does this flag belong to?",
                R.drawable.ic_flag_of_india,
                "United Arab Emirates", "Pakistan", "India", "Saudi Arabia",
                3
            )
        )
        questionsList.add(
            Question(
                9,
                "What country does this flag belong to?",
                R.drawable.ic_flag_of_kuwait,
                "Bahrain", "Kuwait", "Qatar", "Oman",
                2
            )
        )
        questionsList.add(
            Question(
                10,
                "What country does this flag belong to?",
                R.drawable.ic_flag_of_new_zealand,
                "New Zealand", "Switzerland", "Ireland", "Austria",
                1
            )
        )

// Finally, return the list of questions
        questionsList


        return questionsList
    }
}