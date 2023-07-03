package com.example.to_docompose.presentation.ui.screens.route.task

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.Flow
import com.example.to_docompose.presentation.viewmodel.task.TaskContract as Contract

@Composable
fun TaskScreenRoute(
    taskId: Int,
    navController: NavHostController,
    state: Contract.State,
    effect: Flow<Contract.Effect>,
    sendEvent: (Contract.Event) -> Unit,
) {
    sendEvent(Contract.Event.GetTask(taskId = taskId))
    val context = LocalContext.current

    fun displayToast(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    LaunchedEffect(Unit) {
        effect.collect { effect ->
            when (effect) {
                is Contract.Effect.NavigateToListScreen -> {
                    navController.popBackStack()
                }

                is Contract.Effect.ShowFieldErrorMessage -> {
                    displayToast("There is invalid fields. Insert at least 2 characters")
                }

                is Contract.Effect.ShowGenericErrorMessage -> {
                    displayToast("A unexpected error occurred")
                }
            }
        }
    }

    TaskScreen(
        toDoTask = state.toDoTask,
        sendEvent = sendEvent
    )
}