package com.example.to_docompose.domain.interactor.list

import android.util.Log
import com.example.to_docompose.data.models.Priority
import com.example.to_docompose.domain.repository.ToDoRepository
import com.example.to_docompose.domain.usecase.GetListByPriorityUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ListInteractorImpl(
    private val repository: ToDoRepository,
    private val getListByPriorityUseCase: GetListByPriorityUseCase
) : ListInteractor {
    override suspend fun getAllTasks(): Flow<ListInteractorResult> {
        return flow {
            runCatching {
                repository.getAllTasks().collect { tasks ->
                    emit(
                        ListInteractorResult.GetTasksSuccessfully(tasks)
                    )
                }
            }.getOrElse { throwable ->
                Log.i("RepositoryError", "${throwable.message}", throwable)
                emit(ListInteractorResult.Error)
            }
        }
    }

    override suspend fun searchDatabase(searchQuery: String): Flow<ListInteractorResult> {
        return flow {
            runCatching {
                repository.searchDatabase(searchQuery).collect { tasks ->
                    emit(
                        ListInteractorResult.GetTasksSuccessfully(tasks)
                    )
                }
            }.getOrElse { throwable ->
                Log.i("RepositoryError", "${throwable.message}", throwable)
                emit(ListInteractorResult.Error)
            }
        }
    }

    override suspend fun orderByPriority(priority: Priority): Flow<ListInteractorResult> {
        return flow {
            runCatching {
                getListByPriorityUseCase(priority).collect { tasks ->
                    emit(
                        ListInteractorResult.GetTasksSuccessfully(tasks)
                    )
                }
            }.getOrElse { throwable ->
                Log.i("RepositoryError", "${throwable.message}", throwable)
                emit(ListInteractorResult.Error)
            }
        }
    }

    override suspend fun deleteAllTasks() {
        runCatching {
            repository.deleteAllTasks()
        }.getOrElse { throwable ->
            Log.i("RepositoryError", "${throwable.message}", throwable)
        }
    }
}