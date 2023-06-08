package com.example.to_docompose.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_docompose.ui.screens.route.list.ListScreenRoute
import com.example.to_docompose.ui.screens.route.task.TaskScreen
import com.example.to_docompose.ui.screens.route.task.TaskScreenRoute
import com.example.to_docompose.util.Action
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
        ListScreenRoute(
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
        TaskScreenRoute(
            navController = navController
        )
    }

}