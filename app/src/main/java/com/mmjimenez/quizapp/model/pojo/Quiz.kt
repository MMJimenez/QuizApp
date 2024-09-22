package com.mmjimenez.quizapp.model.pojo

class Quiz (
    val id: Int? = null,
    val questions: List<Question> = listOf(),
    var actualQuestion: Int = 0,
    var failed: Int = 0,
    var correct: Int = 0,
)