package com.example.to_docompose.domain.interactor.task

import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.domain.interactor.list.ListInteractorResult

sealed interface TaskInteractorResult {

    data class GetTaskSuccessfully(
        val task: ToDoTask
    ) : TaskInteractorResult

    object Error : TaskInteractorResult
}