package com.example.to_docompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.to_docompose.util.Constants.LIST_SCREEN

@Composable
fun SetupNavigation(
    navControler: NavHostController
) {

    val screen = remember(navControler) { Navigations(navControler) }

    NavHost(navController = navControler, startDestination = LIST_SCREEN) {
        listComposable(
            navigateToTaskScreen = screen.taskScreen
        )
        taskComposable(
            navigateToListScreen = screen.listScreen
        )
    }

}