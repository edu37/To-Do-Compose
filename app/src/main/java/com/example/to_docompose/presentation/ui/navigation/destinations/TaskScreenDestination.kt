package com.example.to_docompose.presentation.ui.navigation.destinations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.to_docompose.presentation.ui.screens.route.task.TaskScreenRoute
import com.example.to_docompose.presentation.viewmodel.task.TaskViewModel

@Composable
fun TaskScreenDestination(
    navController: NavHostController,
    taskId: Int
) {
    val viewModel = hiltViewModel<TaskViewModel>()

    TaskScreenRoute(
        isNewTask = taskId == -1,
        navController = navController,
        state = viewModel.state.collectAsState().value,
        effect = viewModel.effect,
        sendEvent = { event ->
            viewModel.handleEvents(event)
        }
    )
}