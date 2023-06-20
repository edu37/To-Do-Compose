package com.example.to_docompose.presentation.viewmodel.list

import androidx.lifecycle.viewModelScope
import com.example.to_docompose.data.models.Priority
import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.domain.interactor.list.ListInteractor
import com.example.to_docompose.domain.interactor.list.ListInteractorResult
import com.example.to_docompose.presentation.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val interactor: ListInteractor
) : BaseViewModel<ListContract.Event, ListContract.State, ListContract.Effect>() {
    override fun setInitialState() = ListContract.State()

    override fun handleEvents(event: ListContract.Event) {
        viewModelScope.launch {
            when (event) {
                is ListContract.Event.OnTaskClicked -> {
                    navigateToTaskScreen(taskId = event.taskId)
                }

                is ListContract.Event.OnFabClicked -> {
                    navigateToTaskScreen(taskId = -1)
                }

                is ListContract.Event.GetAllTasks -> {
                    getAllTasks()
                }

                is ListContract.Event.SearchTasks -> {
                    searchOnDatabase(input = event.input)
                }

                is ListContract.Event.OrderTasksByPriority -> {
                    orderTasksByPriority(priority = event.priority)
                }

                is ListContract.Event.DeleteAllTasks -> {
                    deleteAll()
                }
            }
        }
    }

    private fun navigateToTaskScreen(taskId: Int) {
        setEffect { ListContract.Effect.NavigateToTaskScreen(taskId) }
    }

    private suspend fun getAllTasks() {
        interactor.getAllTasks().collect {
            when (it) {
                is ListInteractorResult.GetTasksSuccessfully -> {
                    saveList(it.taskList)
                }

                is ListInteractorResult.Error -> {

                }
            }
        }
    }

    private suspend fun searchOnDatabase(input: String) {
        interactor.searchDatabase(input).collect {
            when (it) {
                is ListInteractorResult.GetTasksSuccessfully -> {
                    saveList(it.taskList)
                }

                is ListInteractorResult.Error -> {

                }
            }
        }
    }

    private suspend fun orderTasksByPriority(priority: Priority) {
        interactor.orderByPriority(priority).collect {
            when (it) {
                is ListInteractorResult.GetTasksSuccessfully -> {
                    saveList(it.taskList)
                }

                is ListInteractorResult.Error -> {

                }
            }
        }
    }

    private suspend fun deleteAll() = interactor.deleteAllTasks()

    private fun saveList(list: List<ToDoTask>) {
        setState {
            copy(
                isLoading = false,
                taskList = list,
            )
        }
    }

}