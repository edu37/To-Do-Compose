package com.example.to_docompose.domain.interactor.task

import com.example.to_docompose.data.models.ToDoTask
import kotlinx.coroutines.flow.Flow

interface TaskInteractor {

    suspend fun addTask(toDoTask: ToDoTask)
    fun getSelectedTask(taskId: Int): Flow<ToDoTask>

    suspend fun deleteTask(toDoTask: ToDoTask)
}