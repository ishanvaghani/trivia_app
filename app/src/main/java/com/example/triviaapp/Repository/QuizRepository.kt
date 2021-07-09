package com.example.triviaapp.Repository

import com.example.triviaapp.Dao.QuizDao
import com.example.triviaapp.Model.Quiz
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class QuizRepository @Inject constructor(private val quizDao: QuizDao) {

    val getQuizData: Flow<List<Quiz>> = quizDao.getQuizData()

    suspend fun insert(quiz: Quiz) = withContext(Dispatchers.IO)
    {
        quizDao.insert(quiz)
    }
}