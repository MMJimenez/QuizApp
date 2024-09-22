package com.mmjimenez.quizapp.model.repository

import com.mmjimenez.quizapp.model.QuizResIds
import com.mmjimenez.quizapp.model.pojo.Quiz

interface QuizRepository {
    fun getQuiz(id: Int): Quiz
    suspend fun preloadQuizs()

    fun getActualQuiz(): Quiz?

    fun startNewQuiz(quiz: QuizResIds): Quiz
}