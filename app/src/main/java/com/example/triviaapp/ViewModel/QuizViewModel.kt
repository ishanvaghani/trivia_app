package com.example.triviaapp.ViewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.triviaapp.Model.Quiz
import com.example.triviaapp.Repository.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class QuizViewModel @ViewModelInject constructor(private val quizRepository: QuizRepository) : ViewModel() {

    val getQuizData: LiveData<List<Quiz>> = quizRepository.getQuizData
        .flowOn(Dispatchers.Main).asLiveData(context = viewModelScope.coroutineContext)

    fun insert(quiz: Quiz) = viewModelScope.launch {
        quizRepository.insert(quiz)
    }
}