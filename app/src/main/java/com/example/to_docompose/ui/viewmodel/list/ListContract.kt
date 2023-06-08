package com.example.to_docompose.ui.viewmodel.list

import com.example.to_docompose.ui.viewmodel.UiEffect
import com.example.to_docompose.ui.viewmodel.UiEvent
import com.example.to_docompose.ui.viewmodel.UiState

object ListContract {

    interface Event : UiEvent {

    }

    interface Effect : UiEffect {

    }

    data class State(

    ) : UiState
}