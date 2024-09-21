package com.mmjimenez.quizapp.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Quiz")
data class QuizEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var questionsId: List<Int> = listOf()
)