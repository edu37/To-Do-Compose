package com.example.to_docompose.presentation.viewmodel.task

import com.example.to_docompose.presentation.viewmodel.UiEffect
import com.example.to_docompose.presentation.viewmodel.UiEvent
import com.example.to_docompose.presentation.viewmodel.UiState

object TaskContract {

    interface Event : UiEvent {

    }

    interface Effect : UiEffect {

    }

    data class State(
        val taskId: Int = -1
    ) : UiState
}