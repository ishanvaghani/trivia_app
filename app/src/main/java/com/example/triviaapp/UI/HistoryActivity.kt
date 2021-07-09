package com.example.triviaapp.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.triviaapp.Adapter.QuizAdapter
import com.example.triviaapp.Model.Quiz
import com.example.triviaapp.ViewModel.QuizViewModel
import com.example.triviaapp.databinding.ActivityHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private lateinit var quizAdapter: QuizAdapter
    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        // Observe Live Data of Quiz
        quizViewModel.getQuizData.observe(this, {
            quizAdapter.setData(it)
            binding.history.isVisible = it.isEmpty()
        })
    }

    private fun initRecyclerView() {
        quizAdapter = QuizAdapter(this, ArrayList())
        binding.historyRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@HistoryActivity)
            adapter = quizAdapter
        }
    }
}