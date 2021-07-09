package com.example.triviaapp.Data

import com.example.triviaapp.Model.QuizQuestion

// Quiz Questions
fun questionsList(): List<QuizQuestion> {
    val questions = ArrayList<QuizQuestion>()
    questions.add(
        QuizQuestion(
            "Who is the best cricketer in the world?",
            listOf("Sachin Tendulkar", "Virat Kolli", "Adam Gilchirst", "Jacques Kallis"), false
        )
    )
    questions.add(
        QuizQuestion(
            "What are the colors in the Indian national flag?",
            listOf("White", "Yellow", "Orange", "Green"), true
        )
    )
    return questions
}