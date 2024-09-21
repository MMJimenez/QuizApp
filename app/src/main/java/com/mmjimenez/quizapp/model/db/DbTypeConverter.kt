package com.mmjimenez.quizapp.model.db

import androidx.room.TypeConverter
import com.mmjimenez.quizapp.model.STRING_DELIMITER_CHAR

class DbTypeConverter {
    @TypeConverter
    public fun fromListString(value: List<String>): String {
        return value.joinToString(STRING_DELIMITER_CHAR)
    }

    @TypeConverter
    public fun toListString(value: String): List<String> {
        return value.split(STRING_DELIMITER_CHAR)
    }

}