package com.example.triviaapp.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.triviaapp.Dao.QuizDao
import com.example.triviaapp.Model.DataConverter
import com.example.triviaapp.Model.Quiz

// Database for Room
@Database(entities = [Quiz::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun quizDao(): QuizDao
}