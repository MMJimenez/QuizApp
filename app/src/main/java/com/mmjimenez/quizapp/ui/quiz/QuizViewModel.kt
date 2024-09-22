package com.mmjimenez.quizapp.ui.quiz

import androidx.lifecycle.ViewModel
import com.mmjimenez.quizapp.model.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository
) : ViewModel() {
    var actualQuiz = quizRepository.getActualQuiz()

    fun getTitleText() =
        "Question ${actualQuiz.actualIndexQuestion + 1} / ${actualQuiz.questions.size}"

    fun getQuizProgress(maxProgress: Int) =
        (((actualQuiz.actualIndexQuestion + 1) * maxProgress) / actualQuiz.questions.size)

    fun getQuestionTitle() = with(actualQuiz) {
        questions[actualIndexQuestion].title
    }

    fun getOptions() = with(actualQuiz) {
        questions[actualIndexQuestion].options
    }
    private fun getAnswer() = with(actualQuiz) {
        questions[actualIndexQuestion].answer
    }

    fun passToNextQuestion(): Boolean {
        if (actualQuiz.actualIndexQuestion < actualQuiz.questions.size - 1) {
            actualQuiz.actualIndexQuestion = actualQuiz.actualIndexQuestion + 1
            return true
        }
        return false
    }

    fun getCorrectOption() = getOptions().indexOf(getAnswer())

    fun checkCorrectAnswers(option: String) = (option == getAnswer()).also { isCorrect ->
        if (isCorrect) {
            actualQuiz.correct = actualQuiz.correct + 1
        } else {
            actualQuiz.failed = actualQuiz.failed + 1
        }
    }
}

