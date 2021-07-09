package com.example.triviaapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "quiz")
data class Quiz(
    val quizItems: List<QuizItem>,
    val dateTime: String,
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}