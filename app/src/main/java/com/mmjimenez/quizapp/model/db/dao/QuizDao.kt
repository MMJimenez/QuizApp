package com.mmjimenez.quizapp.model.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mmjimenez.quizapp.model.entity.QuizEntity

@Dao
interface QuizDao {
    @Insert
    fun set(quizEntity: QuizEntity)

    @Query("SELECT * FROM Quiz")
    fun getAll(): List<QuizEntity>
}