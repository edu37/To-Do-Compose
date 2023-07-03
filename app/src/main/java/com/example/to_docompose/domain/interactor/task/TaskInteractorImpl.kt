package com.example.to_docompose.domain.interactor.task

import android.util.Log
import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.domain.repository.ToDoRepository
import com.example.to_docompose.domain.usecase.ValidateTaskFieldsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TaskInteractorImpl(
    private val repository: ToDoRepository,
    private val validateTaskFieldsUseCase: ValidateTaskFieldsUseCase
) : TaskInteractor {
    override fun getSelectedTask(taskId: Int): Flow<TaskInteractorResult> {
        return flow {
            runCatching {
                repository.getSelectedTask(taskId).collect { task ->
                    emit(TaskInteractorResult.GetTaskSuccessfully(task))
                }
            }.getOrElse { throwable ->
                Log.i("RepositoryError", "${throwable.message}", throwable)
                emit(TaskInteractorResult.GenericError)
            }
        }
    }

    override suspend fun addTask(toDoTask: ToDoTask): TaskInteractorResult {
        return runCatching {
            val isValid = validateTaskFieldsUseCase(toDoTask)
            if (isValid) {
                repository.addTask(toDoTask)
                TaskInteractorResult.AddTaskSuccessfully
            } else {
                TaskInteractorResult.InvalidFieldError
            }
        }.getOrElse { throwable ->
            Log.i("RepositoryError", "${throwable.message}", throwable)
            TaskInteractorResult.GenericError
        }
    }

    //TODO "Modificar o retorno para ficar padronizado como o de adicionar e pegar tasks"
    override suspend fun updateTask(toDoTask: ToDoTask): TaskInteractorResult {
        return runCatching {
            val isValid = validateTaskFieldsUseCase(toDoTask)
            if (isValid) {
                repository.updateTask(toDoTask)
                TaskInteractorResult.AddTaskSuccessfully
            } else {
                TaskInteractorResult.InvalidFieldError
            }
        }.getOrElse { throwable ->
            Log.i("RepositoryError", "${throwable.message}", throwable)
            TaskInteractorResult.GenericError
        }
    }

    //TODO "Modificar o retorno para ficar padronizado como o de adicionar e pegar tasks"
    override suspend fun deleteTask(toDoTask: ToDoTask): TaskInteractorResult {
        return runCatching {
            repository.deleteTask(toDoTask)
            TaskInteractorResult.DeleteTaskSuccessfully
        }.getOrElse { throwable ->
            Log.i("RepositoryError", "${throwable.message}", throwable)
            TaskInteractorResult.GenericError
        }
    }

}