package com.mmjimenez.quizapp.model.pojo

data class Question(
    var id: Int? = null,
    var title: String = "",
    var options: List<String> = listOf(),
    var answer: String = "",
)