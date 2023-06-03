package com.example.to_docompose.di

import android.content.Context
import androidx.room.Room
import com.example.to_docompose.data.local.ToDoDao
import com.example.to_docompose.data.local.ToDoDatabase
import com.example.to_docompose.data.repository.ToDoRepositoryImpl
import com.example.to_docompose.domain.repository.ToDoRepository
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


    fun providesToDoRepository(toDoDao: ToDoDao): ToDoRepository {
        return ToDoRepositoryImpl(toDoDao)
    }
}