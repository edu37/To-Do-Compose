package com.example.to_docompose.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_docompose.ui.screens.route.list.ListScreenRoute
import com.example.to_docompose.ui.screens.route.task.TaskScreen
import com.example.to_docompose.util.Action
import com.example.to_docompose.util.Constants

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (Int) -> Unit
) {
    composable(
        route = Constants.LIST_SCREEN,
        arguments = listOf(navArgument(Constants.LIST_ARGUMENT) {
            type = NavType.StringType
        })
    ) {
        ListScreenRoute()
    }
}

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = Constants.TASK_SCREEN,
        arguments = listOf(navArgument(Constants.TASK_ARGUMENT) {
            type = NavType.IntType
        })
    ) {
        TaskScreen()
    }

}