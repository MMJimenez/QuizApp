package com.mmjimenez.quizapp.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Question")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var title: String = "",
    var options: List<String> = listOf(),
    var answer: String = "",
    var quizId: Int? = null,
)