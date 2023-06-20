package com.example.to_docompose.domain.interactor.list

import com.example.to_docompose.data.models.ToDoTask

sealed interface ListInteractorResult {

    data class GetTasksSuccessfully(
        val taskList: List<ToDoTask>
    ) : ListInteractorResult

    object Error : ListInteractorResult

}