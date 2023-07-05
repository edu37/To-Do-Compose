package com.example.to_docompose.presentation.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_docompose.presentation.model.ListArguments
import com.example.to_docompose.presentation.ui.navigation.destinations.ListScreenDestination
import com.example.to_docompose.presentation.ui.navigation.destinations.TaskScreenDestination
import com.example.to_docompose.util.Constants
import com.example.to_docompose.util.Constants.LIST_ARGUMENT_ACTION
import com.example.to_docompose.util.Constants.LIST_ARGUMENT_TASK_TITLE
import com.example.to_docompose.util.Constants.TASK_ARGUMENT

fun NavGraphBuilder.listComposable(
    navController: NavHostController
) {
    composable(
        route = Constants.LIST_SCREEN,
        arguments = listOf(
            navArgument(LIST_ARGUMENT_ACTION) {
                type = NavType.StringType
            },
            navArgument(LIST_ARGUMENT_TASK_TITLE) {
                type = NavType.StringType
            }
        )
    ) {
        val arguments = ListArguments(
            action = it.arguments?.getString(LIST_ARGUMENT_ACTION),
            taskTitle = it.arguments?.getString(LIST_ARGUMENT_TASK_TITLE)
        )

        ListScreenDestination(
            navController = navController,
            arguments = arguments
        )
    }
}

fun NavGraphBuilder.taskComposable(
    navController: NavHostController
) {
    composable(
        route = Constants.TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT) {
            type = NavType.IntType
        })
    ) {
        val taskId = it.arguments!!.getInt(TASK_ARGUMENT)

        TaskScreenDestination(
            navController = navController,
            taskId = taskId
        )
    }
}