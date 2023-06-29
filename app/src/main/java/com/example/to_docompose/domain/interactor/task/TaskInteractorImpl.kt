package com.example.to_docompose.domain.interactor.task

import android.util.Log
import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TaskInteractorImpl(
    private val repository: ToDoRepository
) : TaskInteractor {
    override fun getSelectedTask(taskId: Int): Flow<TaskInteractorResult> {
        return flow {
            runCatching {
                repository.getSelectedTask(taskId).collect { task ->
                    emit(TaskInteractorResult.GetTaskSuccessfully(task))
                }
            }.getOrElse { throwable ->
                Log.i("RepositoryError", "${throwable.message}", throwable)
                emit(TaskInteractorResult.Error)
            }
        }
    }

    override suspend fun addTask(toDoTask: ToDoTask) {
        runCatching {
            repository.addTask(toDoTask)
        }.getOrElse { throwable ->
            Log.i("RepositoryError", "${throwable.message}", throwable)
        }
    }

    override suspend fun updateTask(toDoTask: ToDoTask) {
        runCatching {
            repository.updateTask(toDoTask)
        }.getOrElse { throwable ->
            Log.i("RepositoryError", "${throwable.message}", throwable)
        }
    }

    override suspend fun deleteTask(toDoTask: ToDoTask) {
        runCatching {
            repository.deleteTask(toDoTask)
        }.getOrElse { throwable ->
            Log.i("RepositoryError", "${throwable.message}", throwable)
        }
    }

}