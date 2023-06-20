package com.example.to_docompose.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.to_docompose.data.local.ToDoDao
import com.example.to_docompose.data.local.ToDoDatabase
import com.example.to_docompose.data.repository.ToDoRepositoryImpl
import com.example.to_docompose.domain.interactor.list.ListInteractor
import com.example.to_docompose.domain.interactor.list.ListInteractorImpl
import com.example.to_docompose.domain.repository.ToDoRepository
import com.example.to_docompose.domain.usecase.GetListByPriorityUseCase
import com.example.to_docompose.domain.usecase.GetListByPriorityUseCaseImpl
import com.example.to_docompose.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ToDoDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun providesDao(toDoDatabase: ToDoDatabase) = toDoDatabase.toDoDao()

    @Singleton
    @Provides
    fun providesToDoRepository(toDoDao: ToDoDao): ToDoRepository = ToDoRepositoryImpl(toDoDao)

    @Singleton
    @Provides
    fun providesGetListByPriorityUseCase(repository: ToDoRepository): GetListByPriorityUseCase =
        GetListByPriorityUseCaseImpl(repository)

    @Singleton
    @Provides
    fun providesListInteractor(
        repository: ToDoRepository,
        getListByPriorityUseCase: GetListByPriorityUseCase
    ): ListInteractor =
        ListInteractorImpl(repository, getListByPriorityUseCase)

}