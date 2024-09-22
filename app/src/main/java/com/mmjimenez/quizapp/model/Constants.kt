package com.mmjimenez.quizapp.model

import com.mmjimenez.quizapp.R


class Constants() {
    companion object {
        const val STRING_DELIMITER_CHAR = "|"
    }
}

enum class QuizResIds(val resId: Int, val dbId: Int) {
    QUIZ_1(R.raw.quiz_1, 1)
}