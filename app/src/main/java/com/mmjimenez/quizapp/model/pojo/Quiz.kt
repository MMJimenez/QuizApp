package com.mmjimenez.quizapp.model.pojo

data class Quiz(
    val id: Int? = null,
    val questions: List<Question> = listOf(),
    var actualIndexQuestion: Int = 0,
    var failed: Int = 0,
    var correct: Int = 0,
)