package com.mmjimenez.quizapp.model.repository

import com.mmjimenez.quizapp.model.QuizResIds
import com.mmjimenez.quizapp.model.pojo.Quiz
import com.mmjimenez.quizapp.model.pojo.Score

interface QuizRepository {
    fun getQuiz(id: Int): Quiz
    suspend fun preloadQuizs()
    fun getActualQuiz(): Quiz
    fun startNewQuiz(quiz: QuizResIds): Quiz
    fun startNewQuiz(id: Int): Quiz
    fun getResult(): String

    fun saveScore()

    fun getAllScores(): List<Score>
}