package com.example.triviaapp.Model

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val isMultiChoice: Boolean
)