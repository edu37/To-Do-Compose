package com.example.to_docompose.presentation.viewmodel.task

import com.example.to_docompose.presentation.viewmodel.base.UiEffect
import com.example.to_docompose.presentation.viewmodel.base.UiEvent
import com.example.to_docompose.presentation.viewmodel.base.UiState

object TaskContract {

    interface Event : UiEvent {

    }

    interface Effect : UiEffect {

    }

    data class State(
        val taskId: Int = -1
    ) : UiState
}