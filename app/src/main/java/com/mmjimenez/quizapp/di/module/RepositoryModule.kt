package com.mmjimenez.quizapp.di.module

import com.mmjimenez.quizapp.model.db.dao.QuestionDao
import com.mmjimenez.quizapp.model.repository.QuizRepository
import com.mmjimenez.quizapp.model.repository.QuizRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideQuizRepository(
        questionDao: QuestionDao,
    ): QuizRepository = QuizRepositoryImpl(
        questionDao
    )
}