package com.example.to_docompose.presentation.viewmodel.task

import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.presentation.viewmodel.base.UiEffect
import com.example.to_docompose.presentation.viewmodel.base.UiEvent
import com.example.to_docompose.presentation.viewmodel.base.UiState

object TaskContract {

    interface Event : UiEvent {
        data class GetTask(
            val taskId: Int
        ) : Event

        data class DeleteTask(
            val toDoTask: ToDoTask
        ) : Event

        object GoBackScreen : Event
    }

    interface Effect : UiEffect {
        object NavigateToListScreen : Effect
    }

    data class State(
        val toDoTask: ToDoTask? = null
    ) : UiState
}