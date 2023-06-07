package com.example.to_docompose.ui.navigation

import androidx.navigation.NavHostController
import com.example.to_docompose.util.Action
import com.example.to_docompose.util.Constants.LIST_SCREEN

class Navigations(navController: NavHostController) {

    val taskScreen: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId")
    }

    val listScreen: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }

}