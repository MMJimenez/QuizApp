package com.mmjimenez.quizapp.model.repository

import com.mmjimenez.quizapp.model.QuizResIds
import com.mmjimenez.quizapp.model.db.dao.QuestionDao
import com.mmjimenez.quizapp.model.db.dao.QuizScoreDao
import com.mmjimenez.quizapp.model.entity.QuizScoreEntity
import com.mmjimenez.quizapp.model.mapper.toListQuestionsEntities
import com.mmjimenez.quizapp.model.mapper.toQuestion
import com.mmjimenez.quizapp.model.mapper.toScore
import com.mmjimenez.quizapp.model.pojo.Quiz
import com.mmjimenez.quizapp.model.pojo.Score
import com.mmjimenez.quizapp.model.service.JsonFileService
import timber.log.Timber

class QuizRepositoryImpl(
    private val questionDao: QuestionDao,
    private val quizScoreDao: QuizScoreDao,
    private val jsonFileService: JsonFileService,
) : QuizRepository {
    override fun getQuiz(id: Int) = Quiz(id, questionDao.getAllFromQuiz(id).map { it.toQuestion() })
    override suspend fun preloadQuizs() {
        QuizResIds.entries.forEach { quizResIds ->
            if (existQuizInDb(quizResIds.dbId)) return
            Timber.i("Loading Quiz: ${quizResIds.dbId} in db")
            jsonFileService.readJson(quizResIds.resId)?.let {
                it.toListQuestionsEntities(quizResIds.dbId).forEach { entity ->
                    questionDao.set(entity)
                }
            }
        }
    }

    override fun getActualQuiz(): Quiz = actualQuiz
    override fun startNewQuiz(quiz: QuizResIds): Quiz = getQuiz(quiz.dbId).also { actualQuiz = it }
    override fun startNewQuiz(id: Int): Quiz = getQuiz(id).also { actualQuiz = it }
    private fun existQuizInDb(quizId: Int) = questionDao.checkIfExistsQuiz(quizId) != null

    override fun getResult(): String = with(actualQuiz) {
        val resultPoints = (correct.toDouble() / (questions.size).toDouble() * 10)
        var stringResult = String.format("%.1f", resultPoints)
        if (stringResult.length > 2 && stringResult.takeLast(2) == ",0") {
            stringResult = stringResult.dropLast(2)
        }
        return stringResult
    }

    override fun saveScore() {
        quizScoreDao.set(
            QuizScoreEntity(
                quizId = actualQuiz.id!!,
                result = getResult(),
            )
        )
    }

    override fun getAllScores() = quizScoreDao.getAll().map { it.toScore() }

    companion object {
        private var actualQuiz: Quiz = Quiz()
    }
}
