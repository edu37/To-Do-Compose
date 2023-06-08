package com.example.to_docompose.ui.viewmodel.task

import com.example.to_docompose.ui.viewmodel.UiEffect
import com.example.to_docompose.ui.viewmodel.UiEvent
import com.example.to_docompose.ui.viewmodel.UiState

object TaskContract {

    interface Event: UiEvent {

    }

    interface Effect: UiEffect {

    }

    data class State(

    ): UiState
}