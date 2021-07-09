package com.example.triviaapp.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.triviaapp.Model.Quiz
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {

    // insert quiz to Room
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(quiz: Quiz)

    // get all quiz from Room
    @Query("SELECT * FROM quiz ORDER BY id ASC")
    fun getQuizData(): Flow<List<Quiz>>
}