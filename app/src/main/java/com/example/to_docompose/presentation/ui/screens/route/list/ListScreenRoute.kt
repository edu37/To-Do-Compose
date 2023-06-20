package com.example.to_docompose.presentation.ui.screens.route.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.Flow
import com.example.to_docompose.presentation.viewmodel.list.ListContract as Contract


@Composable
fun ListScreenRoute(
    navController: NavHostController,
    state: Contract.State,
    effect: Flow<Contract.Effect>,
    handleEvent: (Contract.Event) -> Unit,
) {
    LaunchedEffect(key1 = true) {
        handleEvent(Contract.Event.GetAllTasks)
    }

    LaunchedEffect(Unit) {
        effect.collect { effect ->
            when (effect) {
                is Contract.Effect.NavigateToTaskScreen -> {
                    navController.navigate("task/${effect.taskId}")
                }
            }
        }
    }

    ListScreen(
        taskList = state.taskList,
        isLoading = state.isLoading,
        handleEvent = handleEvent
    )
}