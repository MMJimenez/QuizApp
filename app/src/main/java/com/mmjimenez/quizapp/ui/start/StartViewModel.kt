package com.mmjimenez.quizapp.ui.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmjimenez.quizapp.model.QuizResIds
import com.mmjimenez.quizapp.model.pojo.Snackbar
import com.mmjimenez.quizapp.model.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val quizRepository: QuizRepository
) : ViewModel() {

    val snackbarFlow: MutableStateFlow<Snackbar?> = MutableStateFlow(null)

    fun preload() = viewModelScope.launch {
        Timber.i("Init preload of Quiz")
        quizRepository.preloadQuizs()
    }

    private fun showSnackbar(messageId: Int) {
        snackbarFlow.update { Snackbar(messageId) }
    }

    fun startQuiz() {
        quizRepository.startNewQuiz(QuizResIds.QUIZ_1)
    }

    fun getScoreHistory() = quizRepository.getAllScores()
}