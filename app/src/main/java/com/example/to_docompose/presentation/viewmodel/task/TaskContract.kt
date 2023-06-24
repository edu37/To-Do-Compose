package com.example.to_docompose.presentation.viewmodel.task

import com.example.to_docompose.data.models.Priority
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
    }

    interface Effect : UiEffect {
        data class NavigateToListScreen(
            val action: Action
        ) : Effect
    }

    data class State(
        val toDoTask: ToDoTask = ToDoTask(
            DEFAULT_ID,
            DEFAULT_TITLE,
            DEFAULT_DESCRIPTION,
            DEFAULT_PRIORITY
        )
    ) : UiState {

        private companion object {
            const val DEFAULT_ID = -1
            const val DEFAULT_TITLE = ""
            const val DEFAULT_DESCRIPTION = ""
            val DEFAULT_PRIORITY = Priority.NONE
        }
    }
}