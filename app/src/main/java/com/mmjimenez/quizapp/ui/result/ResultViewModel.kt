package com.mmjimenez.quizapp.ui.result

import androidx.lifecycle.ViewModel
import com.mmjimenez.quizapp.model.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.round

@HiltViewModel
class ResultViewModel @Inject constructor(
    val quizRepository: QuizRepository
) : ViewModel() {
    var actualQuiz = quizRepository.getActualQuiz()

    fun getResult(): String = with(actualQuiz) {
        val resultPoints = (correct.toDouble() / (questions.size).toDouble() * 10)
        var stringResult = String.format("%.1f", resultPoints)
        if (stringResult.length > 2 && stringResult.takeLast(2) == ",0") {
            stringResult = stringResult.dropLast(2)
        }
        return stringResult
    }

    fun restartQuiz() {
        actualQuiz.id?.let {
            quizRepository.startNewQuiz(it)
        }
    }
}