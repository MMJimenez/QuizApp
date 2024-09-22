package com.mmjimenez.quizapp.ui.result

import androidx.lifecycle.ViewModel
import com.mmjimenez.quizapp.model.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    val quizRepository: QuizRepository
) : ViewModel() {
    var actualQuiz = quizRepository.getActualQuiz()

    fun getResult(): String = quizRepository.getResult()

    fun saveScore() = quizRepository.saveScore()

    fun restartQuiz() {
        actualQuiz.id?.let {
            quizRepository.startNewQuiz(it)
        }
    }
}