package com.mmjimenez.quizapp.ui.quiz

import androidx.lifecycle.ViewModel
import com.mmjimenez.quizapp.model.pojo.Quiz
import com.mmjimenez.quizapp.model.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber
import javax.inject.Inject
@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository
) : ViewModel() {
    var maxProgress = 0
    val quizFlow: MutableStateFlow<Quiz> = MutableStateFlow(quizRepository.getActualQuiz()!!) // TODO manage this nullable

    fun getTitleText() =
        "Question ${quizFlow.value.actualQuestion + 1} / ${quizFlow.value.questions.size}"

    fun getQuizProgress(maxProgress: Int) =
        (((quizFlow.value.actualQuestion + 1) * maxProgress) / quizFlow.value.questions.size)

    fun getQuestionTitle() = with(quizFlow.value) {
        questions[actualQuestion].title
    }

    fun getOptions() = with(quizFlow.value) {
        questions[actualQuestion].options
    }
    fun getAnswers() = with(quizFlow.value) {
        questions[actualQuestion].answer
    }

    fun newQuestion() {
        quizRepository.getActualQuiz()?.let { quiz ->
            quiz.actualQuestion =+ 1
            quizFlow.update { Quiz() }
            quizFlow.update { quiz }
        }
    }

    fun checkCorrectAnswers(option: String) = (option == getAnswers()).also { isCorrect ->
        if (isCorrect) {
            quizRepository.getActualQuiz()?.correct =+ 1
        } else {
            quizRepository.getActualQuiz()?.failed =+ 1
        }
        Timber.v("Correct: $isCorrect")
    }
}

