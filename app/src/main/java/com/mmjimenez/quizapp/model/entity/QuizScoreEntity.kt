package com.mmjimenez.quizapp.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "QuizScore")
data class QuizScoreEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var quizId: Int = 0,
    var result: String = "",
)