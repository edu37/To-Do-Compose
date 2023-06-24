package com.example.to_docompose.domain.interactor.task

import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow

class TaskInteractorImpl(
    private val repository: ToDoRepository
) : TaskInteractor {

    override fun getSelectedTask(taskId: Int): Flow<ToDoTask> {
        return repository.getSelectedTask(taskId)
    }

}