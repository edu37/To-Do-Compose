package com.example.to_docompose.domain.interactor.list

import com.example.to_docompose.domain.repository.ToDoRepository

class ListInteractorImpl(
    private val repository: ToDoRepository
): ListInteractor {
    override suspend fun getAllTasks() {
        TODO("Not yet implemented")
    }
}