package com.example.to_docompose.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.to_docompose.util.Constants.LIST_SCREEN

@Composable
fun SetupNavigation(
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = LIST_SCREEN) {
        listComposable(navController = navController)
        taskComposable(navController = navController)
    }

}