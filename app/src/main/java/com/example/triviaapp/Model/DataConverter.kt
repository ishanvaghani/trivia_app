package com.example.triviaapp.Model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// Convert Object to Json and Json to Object
class DataConverter {

    private val gson by lazy { Gson() }

    @TypeConverter
    fun fromQuizItemList(value: List<QuizItem>): String {
        val type = object : TypeToken<List<QuizItem>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toQuizItemList(value: String): List<QuizItem> {
        val type = object : TypeToken<List<QuizItem>>() {}.type
        return gson.fromJson(value, type)
    }
}