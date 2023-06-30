package com.example.to_docompose.presentation.di

import com.example.to_docompose.domain.repository.ToDoRepository
import com.example.to_docompose.domain.usecase.GetListByPriorityUseCase
import com.example.to_docompose.domain.usecase.GetListByPriorityUseCaseImpl
import com.example.to_docompose.domain.usecase.ValidateTaskFieldsUseCase
import com.example.to_docompose.domain.usecase.ValidateTaskFieldsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun providesGetListByPriorityUseCase(repository: ToDoRepository): GetListByPriorityUseCase =
        GetListByPriorityUseCaseImpl(repository)

    @Singleton
    @Provides
    fun providesValidateTaskFieldsUseCase(): ValidateTaskFieldsUseCase =
        ValidateTaskFieldsUseCaseImpl()
}