package com.mmjimenez.quizapp.model.service

import org.json.JSONObject

interface JsonFileService {
    fun readJson(resId: Int): JSONObject?
}