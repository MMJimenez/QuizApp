package com.mmjimenez.quizapp.di.module

import android.content.Context
import androidx.room.Room
import com.mmjimenez.quizapp.model.db.ApplicationDb
import com.mmjimenez.quizapp.model.db.DbTypeConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Singleton
    @Provides
    fun provideAplicationDb(
        @ApplicationContext context: Context,
        converter: DbTypeConverter
    ) = Room.databaseBuilder(
        context,
        ApplicationDb::class.java,
        "quiz-database"
    )
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries() // TODO delete line
        .build()

    @Provides
    fun provideConverter() = DbTypeConverter()

    @Singleton
    @Provides
    fun provideQuestionDao(db: ApplicationDb) = db.questionDao()
}