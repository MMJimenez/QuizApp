package com.mmjimenez.quizapp.model.repository

import com.mmjimenez.quizapp.model.db.dao.QuestionDao
import com.mmjimenez.quizapp.model.entity.QuestionEntity
import com.mmjimenez.quizapp.model.pojo.Quiz

class QuizRepositoryImpl(
    private val questionDao: QuestionDao,
) : QuizRepository {

}