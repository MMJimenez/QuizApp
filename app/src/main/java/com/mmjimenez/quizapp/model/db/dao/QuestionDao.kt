package com.mmjimenez.quizapp.model.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mmjimenez.quizapp.model.entity.QuestionEntity

@Dao
interface QuestionDao {
    @Insert
    fun set(questionEntity: QuestionEntity)

    @Query("SELECT * FROM Question WHERE quizId == :quizId")
    fun getAllFromQuiz(quizId: Int): List<QuestionEntity>

    @Query("SELECT 1 FROM Question WHERE quizId == :quizId")
    fun checkIfExistsQuiz(quizId: Int): Int?
}