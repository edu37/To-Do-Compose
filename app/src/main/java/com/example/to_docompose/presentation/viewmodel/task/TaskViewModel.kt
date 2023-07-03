package com.example.to_docompose.presentation.viewmodel.task

import androidx.lifecycle.viewModelScope
import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.domain.interactor.task.TaskInteractor
import com.example.to_docompose.domain.interactor.task.TaskInteractorResult
import com.example.to_docompose.presentation.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    val interactor: TaskInteractor
) : BaseViewModel<TaskContract.Event, TaskContract.State, TaskContract.Effect>() {
    override fun setInitialState() = TaskContract.State()

    override fun handleEvents(event: TaskContract.Event) {
        viewModelScope.launch {
            when (event) {
                is TaskContract.Event.GetTask -> {
                    getTask(event.taskId)
                }

                is TaskContract.Event.GoBackScreen -> {
                    goBackScreen()
                }

                is TaskContract.Event.AddTask -> {
                    addTask(event.toDoTask)
                }

                is TaskContract.Event.UpdateTask -> {
                    updateTask(event.toDoTask)
                }

                is TaskContract.Event.DeleteTask -> {
                    deleteTask(event.toDoTask)
                }
            }
        }
    }

    private fun goBackScreen() {
        setEffect { TaskContract.Effect.NavigateToListScreen }
    }


    private suspend fun getTask(taskId: Int) {
        interactor.getSelectedTask(taskId).collect { result ->
            when (result) {
                is TaskInteractorResult.GetTaskSuccessfully -> {
                    setState {
                        copy(
                            toDoTask = result.task
                        )
                    }
                }

                is TaskInteractorResult.GenericError -> {

                }

                else -> {}
            }
        }
    }

    private suspend fun addTask(toDoTask: ToDoTask) {
        when (interactor.addTask(toDoTask)) {
            is TaskInteractorResult.AddTaskSuccessfully -> {
                goBackScreen()
            }

            is TaskInteractorResult.InvalidFieldError -> {

            }

            is TaskInteractorResult.GenericError -> {

            }

            else -> {}
        }
    }

    private suspend fun updateTask(toDoTask: ToDoTask) {
        when (interactor.updateTask(toDoTask)) {
            is TaskInteractorResult.AddTaskSuccessfully -> {
                goBackScreen()
            }

            is TaskInteractorResult.InvalidFieldError -> {
                showErrorMessage(true)
            }

            is TaskInteractorResult.GenericError -> {
                showErrorMessage()
            }

            else -> {}
        }
    }

    private fun showErrorMessage(isFieldInvalid: Boolean = false) {
        if (isFieldInvalid)
            setEffect { TaskContract.Effect.ShowFieldErrorMessage }
        else
            setEffect { TaskContract.Effect.ShowGenericErrorMessage }
    }

    private suspend fun deleteTask(toDoTask: ToDoTask) {
        interactor.deleteTask(toDoTask)
        goBackScreen()
    }
}
