package com.example.to_docompose.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

interface UiEvent

interface UiState

interface UiEffect

const val SIDE_EFFECTS_KEY = "side-effects_key"

abstract class BaseViewModel<ViewEvent : UiEvent, ViewState : UiState, ViewEffect : UiEffect> :
    ViewModel() {

    abstract fun setInitialState(): ViewState
    abstract fun handleEvents(event: ViewEvent)

    private val initialState: ViewState by lazy { setInitialState() }

    private val _state: MutableState<ViewState> = mutableStateOf(initialState)
    val state: State<ViewState> = _state

    private val _event: MutableSharedFlow<ViewEvent> = MutableSharedFlow()

    private val _effect: Channel<ViewEffect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        viewModelScope.launch {
            _event.collect {
                handleEvents(it)
            }
        }
    }

    fun setEvent(event: ViewEvent) {
        viewModelScope.launch { _event.emit(event) }
    }

    protected fun setState(reducer: ViewState.() -> ViewState) {
        val newState = state.value.reducer()
        _state.value = newState
    }

    protected fun setEffect(builder: () -> ViewEffect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

}