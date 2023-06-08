package com.example.to_docompose.ui.screens.route.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.to_docompose.ui.viewmodel.list.ListContract
import com.example.to_docompose.ui.viewmodel.list.ListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach


@Composable
fun ListScreenRoute(
    navController: NavHostController,
) {
    val viewModel: ListViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.effect?.onEach { effect ->
            when (effect) {
                is ListContract.Effect.NavigateToTaskScreen -> {
                    navController.navigate("task/${effect.taskId}")
                }
            }
        }?.collect()
    }

    ListScreen(
        onFabClicked = { taskId ->
            viewModel.setEvent(ListContract.Event.OnFabClicked(taskId))
        },
        state = viewModel.state.value
    )
}