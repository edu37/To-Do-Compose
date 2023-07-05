package com.example.to_docompose.presentation.viewmodel.task

import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.presentation.viewmodel.base.UiEffect
import com.example.to_docompose.presentation.viewmodel.base.UiEvent
import com.example.to_docompose.presentation.viewmodel.base.UiState
import com.example.to_docompose.util.Action

object TaskContract {

    interface Event : UiEvent {
        data class GetTask(
            val taskId: Int
        ) : Event

        data class DeleteTask(
            val toDoTask: ToDoTask
        ) : Event

        object GoBackScreen : Event

        data class AddTask(
            val toDoTask: ToDoTask
        ) : Event

        data class UpdateTask(
            val toDoTask: ToDoTask
        ) : Event
    }

    interface Effect : UiEffect {
        data class NavigateToListScreen(
            val action: Action = Action.NoAction,
            val taskTitle: String = ""
        ) : Effect
        object ShowFieldErrorMessage : Effect
        object ShowGenericErrorMessage : Effect
    }

    data class State(
        val toDoTask: ToDoTask? = null
    ) : UiState
}