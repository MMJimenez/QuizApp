package com.mmjimenez.quizapp.model.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mmjimenez.quizapp.model.entity.QuizScoreEntity

@Dao
interface QuizScoreDao {
    @Insert
    fun set(quizScore: QuizScoreEntity)

    @Query("SELECT * FROM QuizScore")
    fun getAll(): List<QuizScoreEntity>
}