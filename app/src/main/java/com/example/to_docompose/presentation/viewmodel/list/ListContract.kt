package com.example.to_docompose.presentation.viewmodel.list

import com.example.to_docompose.presentation.viewmodel.base.UiEffect
import com.example.to_docompose.presentation.viewmodel.base.UiEvent
import com.example.to_docompose.presentation.viewmodel.base.UiState

object ListContract {
    interface Event : UiEvent {
        data class OnFabClicked(
            val taskId: Int
        ) : Event
        object GetAllTasks: Event
    }

    interface Effect : UiEffect {
        data class NavigateToTaskScreen(
            val taskId: Int
        ) : Effect
    }

    data class State(
        val taskId: Int = -1
    ) : UiState
}