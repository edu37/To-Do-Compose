package com.example.to_docompose.domain.interactor.list

import com.example.to_docompose.data.models.Priority
import kotlinx.coroutines.flow.Flow

interface ListInteractor {

    suspend fun getAllTasks(): Flow<ListInteractorResult>
    suspend fun searchDatabase(searchQuery: String): Flow<ListInteractorResult>

    suspend fun orderByPriority(priority: Priority): Flow<ListInteractorResult>

    suspend fun deleteAllTasks()
}