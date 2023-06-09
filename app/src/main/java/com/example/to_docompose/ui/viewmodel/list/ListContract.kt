package com.example.to_docompose.ui.viewmodel.list

import com.example.to_docompose.ui.viewmodel.UiEffect
import com.example.to_docompose.ui.viewmodel.UiEvent
import com.example.to_docompose.ui.viewmodel.UiState

object ListContract {

    interface Event : UiEvent {
        data class OnFabClicked(
            val taskId: Int
        ) : Event

        object OnSearchClicked: Event
        object OnCloseSearchClicked: Event
    }

    interface Effect : UiEffect {
        data class NavigateToTaskScreen(
            val taskId: Int
        ) : Effect
    }

    data class State(
        val taskId: Int = -1,
        val isSearching: Boolean = false
    ) : UiState
}