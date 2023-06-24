package com.example.to_docompose.presentation.ui.screens.route.task

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.Flow
import com.example.to_docompose.presentation.viewmodel.task.TaskContract as Contract

@Composable
fun TaskScreenRoute(
    isNewTask: Boolean,
    navController: NavHostController,
    state: Contract.State,
    effect: Flow<Contract.Effect>,
    sendEvent: (Contract.Event) -> Unit,
) {
    val task = state.toDoTask
    if (!isNewTask) sendEvent(Contract.Event.GetTask(task.id))

    LaunchedEffect(Unit) {
        effect.collect { effect ->
            when (effect) {
                is Contract.Effect.NavigateToListScreen -> {
                    navController.navigate("list/${effect.action}")
                }
            }
        }
    }

    TaskScreen(
        isNewTask = isNewTask,
        toDoTask = task,
        sendEvent = sendEvent
    )
}