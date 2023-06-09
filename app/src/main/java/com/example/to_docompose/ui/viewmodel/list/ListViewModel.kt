package com.example.to_docompose.ui.viewmodel.list

import androidx.lifecycle.viewModelScope
import com.example.to_docompose.domain.repository.ToDoRepository
import com.example.to_docompose.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: ToDoRepository
) : BaseViewModel<ListContract.Event, ListContract.State, ListContract.Effect>() {
    override fun setInitialState() = ListContract.State()

    override fun handleEvents(event: ListContract.Event) {
        viewModelScope.launch {
            when (event) {
                is ListContract.Event.OnFabClicked -> {
                    setEffect { ListContract.Effect.NavigateToTaskScreen(event.taskId) }
                }
                is ListContract.Event.OnSearchClicked -> {
                    setState { copy(isSearching = true) }
                }
                is ListContract.Event.OnCloseSearchClicked -> {
                    setState { copy(isSearching = false) }
                }
            }
        }
    }

}