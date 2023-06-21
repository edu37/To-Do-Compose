package com.example.to_docompose.presentation.viewmodel.task

import com.example.to_docompose.data.models.Priority
import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.presentation.viewmodel.base.UiEffect
import com.example.to_docompose.presentation.viewmodel.base.UiEvent
import com.example.to_docompose.presentation.viewmodel.base.UiState
import com.example.to_docompose.util.Action

object TaskContract {

    interface Event : UiEvent {

    }

    interface Effect : UiEffect {
        data class NavigateToListScreen(
            val action: Action
        ) : Effect
    }

    data class State(
        val toDoTask: ToDoTask = ToDoTask(0, "", "", Priority.NONE)
    ) : UiState
}