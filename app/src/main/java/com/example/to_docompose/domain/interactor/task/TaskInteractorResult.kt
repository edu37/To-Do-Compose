package com.example.to_docompose.domain.interactor.task

import com.example.to_docompose.data.models.ToDoTask

sealed interface TaskInteractorResult {

    data class GetTaskSuccessfully(
        val task: ToDoTask
    ) : TaskInteractorResult

    object AddTaskSuccessfully : TaskInteractorResult
    object DeleteTaskSuccessfully : TaskInteractorResult

    object GenericError : TaskInteractorResult

    object InvalidFieldError : TaskInteractorResult
}