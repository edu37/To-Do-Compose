package com.example.to_docompose.presentation.ui.screens.route.task

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.Flow
import com.example.to_docompose.presentation.viewmodel.task.TaskContract as Contract

@Composable
fun TaskScreenRoute(
    taskId: Int,
    navController: NavHostController,
    state: Contract.State,
    effect: Flow<Contract.Effect>,
    sendEvent: (Contract.Event) -> Unit,
) {
    sendEvent(Contract.Event.GetTask(taskId = taskId))

    LaunchedEffect(Unit) {
        effect.collect { effect ->
            when (effect) {
                is Contract.Effect.NavigateToListScreen -> {
                    navController.popBackStack()
                }
            }
        }
    }

    TaskScreen(
        toDoTask = state.toDoTask,
        sendEvent = sendEvent
    )
}