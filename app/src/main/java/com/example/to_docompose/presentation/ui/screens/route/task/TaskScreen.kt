package com.example.to_docompose.presentation.ui.screens.route.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.presentation.ui.components.toolbar.TaskScreenTopBar
import com.example.to_docompose.presentation.viewmodel.task.TaskContract

@Composable
fun TaskScreen(
    isNewTask: Boolean,
    toDoTask: ToDoTask,
    sendEvent: (TaskContract.Event) -> Unit
) {

    Scaffold(
        topBar = {
            TaskScreenTopBar(
                isNewTask = isNewTask,
                toDoTask = toDoTask,
                sendEvent = sendEvent
            )
        }
    ) {

    }

}