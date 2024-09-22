package com.mmjimenez.quizapp.di.module

import android.content.Context
import com.mmjimenez.quizapp.model.service.JsonFileService
import com.mmjimenez.quizapp.model.service.JsonFileServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    fun provideJsonFileService(@ApplicationContext context: Context): JsonFileService =
        JsonFileServiceImpl(context)
}