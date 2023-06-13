package com.example.to_docompose.presentation.viewmodel.list

import com.example.to_docompose.presentation.viewmodel.UiEffect
import com.example.to_docompose.presentation.viewmodel.UiEvent
import com.example.to_docompose.presentation.viewmodel.UiState

object ListContract {
    interface Event : UiEvent {
        data class OnFabClicked(
            val taskId: Int
        ) : Event
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