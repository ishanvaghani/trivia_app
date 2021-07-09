package com.example.triviaapp.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.core.view.allViews
import androidx.core.view.forEach
import androidx.core.view.isVisible
import androidx.viewpager.widget.PagerAdapter
import com.example.triviaapp.Model.QuizQuestion
import com.example.triviaapp.R
import com.example.triviaapp.databinding.QuizItemBinding

class ViewPagerAdapter(
    private val context: Context,
    private val quizQuestionsList: List<QuizQuestion>,
    private val optionSelected: OptionSelected
) : PagerAdapter() {

    private lateinit var layoutInflater: LayoutInflater

    override fun getCount(): Int = quizQuestionsList.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val quizQuestion = quizQuestionsList[position]

        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = QuizItemBinding.inflate(layoutInflater, container, false)

        //set up each adapter item
        binding.apply {
            question.text = quizQuestion.question
            if (quizQuestion.isMultiChoice) {
                checkboxGroup.isVisible = true
                quizQuestion.options.forEachIndexed { index, option ->
                    val checkBox = CheckBox(context)
                    checkBox.text = option
                    checkBox.id = index
                    checkboxGroup.addView(checkBox)
                }
            } else {
                radioGroup.isVisible = true
                quizQuestion.options.forEachIndexed { index, option ->
                    val radioButton = RadioButton(context)
                    radioButton.text = option
                    radioButton.id = index
                    radioGroup.addView(radioButton)
                }
            }

            if(radioGroup.isVisible) {
                radioGroup.setOnCheckedChangeListener { _, checkedId ->
                    val list = ArrayList<String>()
                    list.add(radioGroup.findViewById<RadioButton>(checkedId).text.toString())
                    optionSelected.optionSelected(list)
                }
            } else {
                checkboxGroup.allViews.iterator().forEach { checkBox ->
                    checkBox.setOnClickListener {
                        val list = ArrayList<String>()
                        checkboxGroup.forEach {
                            if((it as CheckBox).isChecked) {
                                list.add(it.text.toString())
                            }
                        }
                        optionSelected.optionSelected(list)
                    }
                }
            }
        }

        container.addView(binding.root)

        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    interface OptionSelected {

        fun optionSelected(answers: ArrayList<String>)
    }
}