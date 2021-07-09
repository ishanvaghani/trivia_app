package com.example.triviaapp.DI

import android.content.Context
import androidx.room.Room
import com.example.triviaapp.Dao.QuizDao
import com.example.triviaapp.Database.QuizDatabase
import com.example.triviaapp.Repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    //Dependency Injection for database
    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): QuizDatabase =
        Room.databaseBuilder(context, QuizDatabase::class.java, "quizDatabase")
            .build()


    //Dependency Injection for Dao
    @Provides
    fun provideQuizDao(quizDatabase: QuizDatabase): QuizDao =
        quizDatabase.quizDao()


    //Dependency Injection for Repository
    @Provides
    fun provideQuizRepository(quizDao: QuizDao) : QuizRepository =
        QuizRepository(quizDao)
}