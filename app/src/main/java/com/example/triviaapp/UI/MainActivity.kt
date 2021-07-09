package com.example.triviaapp.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.triviaapp.Adapter.QuizItemAdapter
import com.example.triviaapp.Adapter.ViewPagerAdapter
import com.example.triviaapp.Data.questionsList
import com.example.triviaapp.Model.Quiz
import com.example.triviaapp.Model.QuizItem
import com.example.triviaapp.R
import com.example.triviaapp.ViewModel.QuizViewModel
import com.example.triviaapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ViewPagerAdapter.OptionSelected {

    private lateinit var binding: ActivityMainBinding
    private val quizViewModel: QuizViewModel by viewModels()
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    private var currentPage: Int = 0
    private val questions = questionsList()
    private val quizItems = ArrayList<QuizItem>()

    private var currentQuestionAnswer: ArrayList<String> = ArrayList()
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm a")
    private lateinit var userName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNamePage()
    }

    // Setup Enter username
    private fun setupNamePage() {
        binding.apply {
            nameLayout.apply {
                root.isVisible = true

                button.setOnClickListener {
                    if(nameLayout.nameEdittext.text.isEmpty()) {
                        nameLayout.nameEdittext.error = "Name can't be empty"
                    } else {
                        userName = nameLayout.nameEdittext.text.toString()
                        nameLayout.root.isVisible = false
                        questionsLayout.root.isVisible = true
                        setupQuiz()
                    }
                }

                nameEdittext.text.clear()
            }
        }
    }

    // Setup Quiz Questions
    private fun setupQuiz() {
        viewPagerAdapter = ViewPagerAdapter(this, questions, this)
        binding.apply {
            questionsLayout.apply {
                viewPager.adapter = viewPagerAdapter

                viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                    }

                    override fun onPageSelected(position: Int) {
                        currentPage = position
                        addButtonText(position)
                    }

                    override fun onPageScrollStateChanged(state: Int) {
                    }
                })

                button.setOnClickListener {
                    if(currentQuestionAnswer.isEmpty()) {
                        Toast.makeText(this@MainActivity, "Please Select option", Toast.LENGTH_SHORT).show()
                    } else {
                        quizItems.add(
                            QuizItem(
                                questions[currentPage].question,
                                currentQuestionAnswer
                            )
                        )
                        Log.d("MainActivity", quizItems.toString())
                        currentQuestionAnswer = ArrayList()
                        if (currentPage < questions.size - 1) {
                            viewPager.currentItem = currentPage + 1
                        } else {
                            questionsLayout.isVisible = false
                            answersLayout.root.isVisible = true
                            setupAnswers()
                        }
                    }
                }
            }
        }
    }

    // Setup our Quiz Answers
    private fun setupAnswers() {
        Log.d("MainActivity", quizItems.toString())

        //save data to Room
        val quiz = Quiz(quizItems,simpleDateFormat.format(Calendar.getInstance().time), userName)
        quizViewModel.insert(quiz)

        binding.apply {
            answersLayout.recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(
                    DividerItemDecoration(
                        context,
                        DividerItemDecoration.VERTICAL
                    )
                )
                adapter = QuizItemAdapter(context, quizItems)
            }

            answersLayout.username.text = userName

            answersLayout.restartButton.setOnClickListener {
                reset()
            }
        }
    }

    // Reset the Quiz
    private fun reset() {
        binding.apply {
            answersLayout.root.isVisible = false
            setupNamePage()
            quizItems.clear()
            currentPage = 0
            binding.questionsLayout.button.text = getString(R.string.next)
        }
    }

    private fun addButtonText(position: Int) {
        if (position == questions.size - 1) {
            binding.questionsLayout.button.text = getString(R.string.finish)
        } else {
            binding.questionsLayout.button.text = getString(R.string.next)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun optionSelected(answers: ArrayList<String>) {
        currentQuestionAnswer = answers
    }
}