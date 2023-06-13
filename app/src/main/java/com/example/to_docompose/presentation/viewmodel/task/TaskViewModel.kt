package com.example.to_docompose.presentation.viewmodel.task

import com.example.to_docompose.presentation.viewmodel.BaseViewModel

class TaskViewModel : BaseViewModel<TaskContract.Event, TaskContract.State, TaskContract.Effect>() {
    override fun setInitialState() = TaskContract.State()

    override fun handleEvents(event: TaskContract.Event) {
        TODO("Not yet implemented")
    }
}