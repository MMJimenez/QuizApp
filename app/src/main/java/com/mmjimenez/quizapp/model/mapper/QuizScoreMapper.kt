package com.mmjimenez.quizapp.model.mapper

import com.mmjimenez.quizapp.model.entity.QuizScoreEntity
import com.mmjimenez.quizapp.model.pojo.Score

fun QuizScoreEntity.toScore() = Score(
    quizId = quizId,
    result = result,
)