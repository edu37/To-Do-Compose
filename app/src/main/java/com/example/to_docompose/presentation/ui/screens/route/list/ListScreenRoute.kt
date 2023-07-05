package com.example.to_docompose.presentation.ui.screens.route.list

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.example.to_docompose.presentation.model.ListArguments
import com.example.to_docompose.presentation.viewmodel.list.ListContract
import com.example.to_docompose.util.Action
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import com.example.to_docompose.presentation.viewmodel.list.ListContract as Contract


@Composable
fun ListScreenRoute(
    navController: NavHostController,
    state: ListContract.State,
    effect: Flow<ListContract.Effect>,
    sendEvent: (ListContract.Event) -> Unit,
    arguments: ListArguments,
) {
    LaunchedEffect(key1 = true) {
        sendEvent(Contract.Event.GetAllTasks)
    }

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    HandleActions(arguments, scaffoldState, coroutineScope)

    LaunchedEffect(Unit) {
        effect.collect { effect ->
            when (effect) {
                is Contract.Effect.NavigateToTaskScreen -> {
                    navController.navigate("task/${effect.taskId}")
                }
            }
        }
    }

    ListScreen(
        scaffoldState = scaffoldState,
        taskList = state.taskList,
        isLoading = state.isLoading,
        sendEvent = sendEvent
    )
}

@Composable
private fun HandleActions(
    arguments: ListArguments,
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope
): Unit = with(arguments) {
    if (action == null) {
        return
    } else {
        when (action) {
            Action.NoAction.name -> {}
            Action.Undo.name -> {

            }

            Action.DeleteAll.name -> {
                DisplaySnackbar(
                    scaffoldState = scaffoldState,
                    coroutineScope = coroutineScope,
                    message = "All Tasks deleted successfully",
                    actionLabel = "Ok"
                )
            }

            else -> {
                DisplaySnackbar(
                    scaffoldState = scaffoldState,
                    coroutineScope = coroutineScope,
                    message = "Task ${arguments.action}: ${arguments.taskTitle}",
                    actionLabel = if (action == Action.Deleted.name) "Undo" else "Ok"
                )
            }
        }
    }
}

@Composable
private fun DisplaySnackbar(
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    message: String,
    actionLabel: String
) {
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = message,
                actionLabel = actionLabel
            )
        }
    }
}
