package com.example.to_docompose.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_docompose.util.Action
import com.example.to_docompose.util.Constants.LIST_SCREEN
import com.example.to_docompose.util.Constants.TASK_SCREEN


fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(ArgumentKeys.TASK_ARGUMENT) {
            type = NavType.IntType
        })
    ) {

    }

}

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (Int) -> Unit
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(ArgumentKeys.LIST_ARGUMENT) {
            type = NavType.StringType
        })
    ) {

    }
}

private object ArgumentKeys {
    const val LIST_ARGUMENT = "action"
    const val TASK_ARGUMENT = "taskId"
}