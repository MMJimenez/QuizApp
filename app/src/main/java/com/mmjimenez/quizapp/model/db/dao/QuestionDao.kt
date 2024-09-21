package com.mmjimenez.quizapp.model.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mmjimenez.quizapp.model.entity.QuestionEntity
import com.mmjimenez.quizapp.model.entity.QuizEntity

@Dao
interface QuestionDao {
    @Insert
    fun set(questionEntity: QuestionEntity)

    @Query("SELECT * FROM Question")
    fun getAll(): List<QuestionEntity>
}