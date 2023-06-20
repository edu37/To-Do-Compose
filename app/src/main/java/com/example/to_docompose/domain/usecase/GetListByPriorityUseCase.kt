package com.example.to_docompose.domain.usecase

import com.example.to_docompose.data.models.Priority
import com.example.to_docompose.data.models.ToDoTask
import kotlinx.coroutines.flow.Flow

interface GetListByPriorityUseCase {

    suspend operator fun invoke(priority: Priority): Flow<List<ToDoTask>>
}