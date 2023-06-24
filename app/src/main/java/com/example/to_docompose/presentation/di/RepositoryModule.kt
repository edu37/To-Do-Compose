package com.example.to_docompose.presentation.di

import com.example.to_docompose.data.local.ToDoDao
import com.example.to_docompose.data.repository.ToDoRepositoryImpl
import com.example.to_docompose.domain.repository.ToDoRepository
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
    fun providesToDoRepository(toDoDao: ToDoDao): ToDoRepository = ToDoRepositoryImpl(toDoDao)
}