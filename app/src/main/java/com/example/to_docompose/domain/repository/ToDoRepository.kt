package com.example.to_docompose.domain.repository

import com.example.to_docompose.data.models.ToDoTask
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    fun getSelectedTask(taskId: Int): Flow<ToDoTask>

    fun getAllTasks(): Flow<List<ToDoTask>>

    fun searchDatabase(searchQuery: String): Flow<List<ToDoTask>>

    fun sortByLowPriority(): Flow<List<ToDoTask>>

    fun sortByHighPriority(): Flow<List<ToDoTask>>

    suspend fun addTask(toDoTask: ToDoTask)

    suspend fun updateTask(toDoTask: ToDoTask)

    suspend fun deleteTask(toDoTask: ToDoTask)

    suspend fun deleteAllTasks()
}