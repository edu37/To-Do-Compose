package com.example.to_docompose.presentation.di

import com.example.to_docompose.domain.interactor.list.ListInteractor
import com.example.to_docompose.domain.interactor.list.ListInteractorImpl
import com.example.to_docompose.domain.interactor.task.TaskInteractor
import com.example.to_docompose.domain.interactor.task.TaskInteractorImpl
import com.example.to_docompose.domain.repository.ToDoRepository
import com.example.to_docompose.domain.usecase.GetListByPriorityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorModule {

    @Singleton
    @Provides
    fun providesListInteractor(
        repository: ToDoRepository,
        getListByPriorityUseCase: GetListByPriorityUseCase
    ): ListInteractor =
        ListInteractorImpl(repository, getListByPriorityUseCase)

    @Singleton
    @Provides
    fun providesTaskInteractor(
        repository: ToDoRepository,
    ): TaskInteractor = TaskInteractorImpl(repository)
}