package com.mmjimenez.quizapp.model.service

import android.content.Context
import android.util.Log
import org.json.JSONObject

class JsonFileServiceImpl(val context: Context) : JsonFileService {
    override fun readJson(resId: Int): JSONObject? = try {
        context.resources.openRawResource(resId).bufferedReader().use {
            JSONObject(it.readText())
        }
    } catch (exception: Exception) {
        Log.e(
            "User App",
            exception.localizedMessage ?: "failed to pre-populate users into database"
        )
        null
    }
}