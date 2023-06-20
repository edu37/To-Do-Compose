package com.example.to_docompose.presentation.viewmodel.list

import com.example.to_docompose.data.models.Priority
import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.presentation.viewmodel.base.UiEffect
import com.example.to_docompose.presentation.viewmodel.base.UiEvent
import com.example.to_docompose.presentation.viewmodel.base.UiState

object ListContract {
    interface Event : UiEvent {
        data class OnTaskClicked(
            val taskId: Int
        ) : Event

        object OnFabClicked : Event

        object GetAllTasks : Event
        data class SearchTasks(
            val input: String
        ) : Event

        data class OrderTasksByPriority(
            val priority: Priority
        ) : Event

        object DeleteAllTasks : Event
    }

    interface Effect : UiEffect {
        data class NavigateToTaskScreen(
            val taskId: Int
        ) : Effect
    }

    data class State(
        val taskList: List<ToDoTask> = listOf(),
        val isLoading: Boolean = true
    ) : UiState
}