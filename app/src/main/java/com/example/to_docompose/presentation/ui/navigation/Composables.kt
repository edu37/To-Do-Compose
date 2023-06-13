package com.example.to_docompose.presentation.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_docompose.presentation.ui.navigation.destinations.ListScreenDestination
import com.example.to_docompose.presentation.ui.navigation.destinations.TaskScreenDestination
import com.example.to_docompose.util.Constants

fun NavGraphBuilder.listComposable(
    navController: NavHostController
) {
    composable(
        route = Constants.LIST_SCREEN,
        arguments = listOf(navArgument(Constants.LIST_ARGUMENT) {
            type = NavType.StringType
        })
    ) {
        ListScreenDestination(
            navController = navController
        )
    }
}

fun NavGraphBuilder.taskComposable(
    navController: NavHostController
) {
    composable(
        route = Constants.TASK_SCREEN,
        arguments = listOf(navArgument(Constants.TASK_ARGUMENT) {
            type = NavType.IntType
        })
    ) {
        TaskScreenDestination(navController = navController)
    }
}