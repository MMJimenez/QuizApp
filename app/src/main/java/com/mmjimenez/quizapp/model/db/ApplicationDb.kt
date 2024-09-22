package com.mmjimenez.quizapp.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mmjimenez.quizapp.model.db.dao.QuestionDao
import com.mmjimenez.quizapp.model.db.dao.QuizScoreDao
import com.mmjimenez.quizapp.model.entity.QuestionEntity
import com.mmjimenez.quizapp.model.entity.QuizScoreEntity

@TypeConverters(DbTypeConverter::class)
@Database(
    entities = [
        QuestionEntity::class,
        QuizScoreEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class ApplicationDb : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
    abstract fun quizScoreDao(): QuizScoreDao
}