package com.example.triviaapp.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.triviaapp.Model.QuizItem
import com.example.triviaapp.databinding.HistoryQuestionItemBinding

class QuizItemAdapter(private val context: Context, private var quizItems: List<QuizItem>) :
    RecyclerView.Adapter<QuizItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("QuizItemAdapter", quizItems.toString())
        val binding =
            HistoryQuestionItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quizItem = quizItems[position]
        holder.bind(quizItem)
    }

    override fun getItemCount(): Int = quizItems.size

    class ViewHolder(private val binding: HistoryQuestionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //set up each adapter item
        fun bind(quizItem: QuizItem) {
            binding.apply {
                question.text = quizItem.question
                var answers = ""
                if (quizItem.answer.size == 1) {
                    answers = quizItem.answer[0]
                } else {
                    for (answer in quizItem.answer) {
                        answers += "$answer, "
                    }
                }
                answer.text = answers
            }
        }
    }
}