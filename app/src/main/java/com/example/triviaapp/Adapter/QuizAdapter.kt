package com.example.triviaapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.triviaapp.Model.Quiz
import com.example.triviaapp.Model.QuizItem
import com.example.triviaapp.databinding.HistoryItemBinding

class QuizAdapter(private val context: Context, private var quizList: List<Quiz>) :
    RecyclerView.Adapter<QuizAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HistoryItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quiz = quizList[position]
        holder.bind(quiz, position)
    }

    override fun getItemCount(): Int = quizList.size

    fun setData(quizList: List<Quiz>) {
        this.quizList = quizList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: HistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //set up each adapter item
        fun bind(quiz: Quiz, position: Int) {
            binding.apply {
                gameNo.text = "GAME ${position+1} : "
                dateTime.text = quiz.dateTime
                questionsRecyclerview.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context)
                    addItemDecoration(
                        DividerItemDecoration(
                            context,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                    adapter = QuizItemAdapter(context, quiz.quizItems)
                }
                username.text = quiz.name
            }
        }
    }
}