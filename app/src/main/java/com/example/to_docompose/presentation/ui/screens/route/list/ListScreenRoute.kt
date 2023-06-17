package com.example.to_docompose.presentation.ui.screens.route.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.to_docompose.presentation.viewmodel.list.ListViewModel
import kotlinx.coroutines.flow.Flow
import com.example.to_docompose.presentation.viewmodel.list.ListContract as Contract


@Composable
fun ListScreenRoute(
    navController: NavHostController,
    state: Contract.State,
    effect: Flow<Contract.Effect>,
    sendEvent: (Contract.Event) -> Unit,
) {

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
        state = state,
        onFabClicked = { taskId ->
            sendEvent(Contract.Event.OnFabClicked(taskId))
        },
    )
}