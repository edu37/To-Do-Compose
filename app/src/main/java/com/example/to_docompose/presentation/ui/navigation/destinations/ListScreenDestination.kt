package com.example.to_docompose.presentation.ui.navigation.destinations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.to_docompose.presentation.ui.screens.route.list.ListScreenRoute
import com.example.to_docompose.presentation.viewmodel.list.ListViewModel

@Composable
fun ListScreenDestination(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<ListViewModel>()

    ListScreenRoute(
        navController = navController,
        state = viewModel.state.collectAsState().value,
        effect = viewModel.effect,
        sendEvent = { event ->
            viewModel.setEvent(event)
        }
    )
}