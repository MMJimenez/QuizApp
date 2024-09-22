package com.mmjimenez.quizapp.model.repository

import com.mmjimenez.quizapp.model.QuizResIds
import com.mmjimenez.quizapp.model.db.dao.QuestionDao
import com.mmjimenez.quizapp.model.mapper.toListQuestionsEntities
import com.mmjimenez.quizapp.model.mapper.toQuestion
import com.mmjimenez.quizapp.model.pojo.Quiz
import com.mmjimenez.quizapp.model.service.JsonFileService
import timber.log.Timber

class QuizRepositoryImpl(
    private val questionDao: QuestionDao,
    private val jsonFileService: JsonFileService,
) : QuizRepository {
    override fun getQuiz(id: Int) = Quiz(id, questionDao.getAllFromQuiz(id).map { it.toQuestion() })
    override suspend fun preloadQuizs() {
        QuizResIds.entries.forEach{ quizResIds ->
            if (existQuizInDb(quizResIds.dbId)) return
            Timber.i("Loading Quiz: ${quizResIds.dbId} in db")
            jsonFileService.readJson(quizResIds.resId)?.let {
                it.toListQuestionsEntities(quizResIds.dbId).forEach { entity ->
                    questionDao.set(entity)
                }
            }
        }
    }

    override fun getActualQuiz(): Quiz = actualQuiz ?: Quiz() // TODO handle this nullable
    override fun startNewQuiz(quiz: QuizResIds): Quiz = getQuiz(quiz.dbId).also { actualQuiz = it }
    override fun startNewQuiz(id: Int): Quiz = getQuiz(id).also { actualQuiz = it }
    private fun existQuizInDb(quizId: Int) = questionDao.checkIfExistsQuiz(quizId) != null

    companion object {
        private var actualQuiz: Quiz? = null
    }
}
