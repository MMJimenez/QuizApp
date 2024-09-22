package com.mmjimenez.quizapp.model.mapper

import com.mmjimenez.quizapp.model.entity.QuestionEntity
import com.mmjimenez.quizapp.model.pojo.Question
import org.json.JSONArray
import org.json.JSONObject

fun QuestionEntity.toQuestion() = Question(
    id = id,
    title = title,
    options = options,
    answer = answer,
)

fun JSONObject.toListQuestionsEntities(quizId: Int): List<QuestionEntity> {
    val questionsList = mutableListOf<QuestionEntity>()
    this.getJSONObject("quiz").getJSONArray("questions").takeIf {
        it.length() > 0
    }?.let { jsonArray ->
        for (index in 0 until jsonArray.length()) {
            val questionJson = jsonArray.getJSONObject(index)

            val questionEntity = QuestionEntity(
                id = questionJson.getInt("id"),
                title = questionJson.getString("question"),
                answer = questionJson.getString("answer"),
                options = questionJson.getJSONArray("options").toListStrings(),
                quizId = quizId,
            )
            questionsList.add(questionEntity)
        }
    }
    return questionsList
}

fun JSONArray.toListStrings() =
    mutableListOf<String>().also { list ->
        for (index in 0 until this.length()) {
            list.add(this.getString(index))
        }
    }
