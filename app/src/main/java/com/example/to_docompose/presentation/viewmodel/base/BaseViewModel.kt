package com.example.to_docompose.presentation.viewmodel.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

interface UiEvent

interface UiState

interface UiEffect

const val SIDE_EFFECTS_KEY = "side-effects_key"

abstract class BaseViewModel<Event : UiEvent, State : UiState, ViewEffect : UiEffect> :
    ViewModel() {

    private val initialState: State by lazy { setInitialState() }
    abstract fun setInitialState(): State

    abstract fun handleEvents(event: Event)

    val currentState: State
        get() = state.value

    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()

    private val _effect: Channel<ViewEffect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        eventCollector()
    }

    private fun eventCollector() {
        viewModelScope.launch {
            _event.collect {
                handleEvents(it)
            }
        }
    }

    fun setEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    protected fun setState(reducer: State.() -> State) {
        val newState = state.value.reducer()
        _state.value = newState
    }

    protected fun setEffect(builder: () -> ViewEffect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

}