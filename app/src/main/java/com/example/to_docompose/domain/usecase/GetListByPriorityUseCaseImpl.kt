package com.example.to_docompose.domain.usecase

import com.example.to_docompose.data.models.Priority
import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow

class GetListByPriorityUseCaseImpl(
    private val repository: ToDoRepository
) : GetListByPriorityUseCase {

    override suspend fun invoke(priority: Priority): Flow<List<ToDoTask>> {
        return when (priority) {
            Priority.HIGH -> {
                repository.sortByHighPriority()
            }

            Priority.LOW -> {
                repository.sortByLowPriority()
            }

            else -> {
                repository.getAllTasks()
            }
        }
    }
}