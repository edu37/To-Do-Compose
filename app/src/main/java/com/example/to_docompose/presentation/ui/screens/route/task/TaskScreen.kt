package com.example.to_docompose.presentation.ui.screens.route.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.presentation.ui.components.toolbar.TaskScreenTopBar
import com.example.to_docompose.presentation.viewmodel.task.TaskContract as Contract

@Composable
fun TaskScreen(
    toDoTask: ToDoTask?,
    sendEvent: (Contract.Event) -> Unit
) {

    Scaffold(
        topBar = {
            TaskScreenTopBar(
                toDoTask = toDoTask,
                onGoBackScreen = { sendEvent(Contract.Event.GoBackScreen) },
                onDeleteClicked = { task ->
                    sendEvent(Contract.Event.DeleteTask(task))
                },
                onConfirmClicked = {},
            )
        }
    ) {
        it
    }

}