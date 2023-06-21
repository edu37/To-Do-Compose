package com.example.to_docompose.presentation.ui.components.toolbar

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.to_docompose.data.models.Priority
import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.presentation.ui.theme.toolbarBackground
import com.example.to_docompose.presentation.ui.theme.toolbarContent
import com.example.to_docompose.util.Action
import com.example.to_docompose.presentation.viewmodel.task.TaskContract as Contract

@Composable
fun TaskScreenTopBar(
    taskId: Int,
    toDoTask: ToDoTask? = null,
    sendEvent: (Contract.Event) -> Unit
) {
    val isNewTask = taskId == -1

    if (isNewTask) {
        CreateTaskTopBar(
            onBackArrowClicked = {

            },
            onConfirmClicked = {

            }
        )
    } else {
        toDoTask?.let {
            EditTaskTopBar(
                toDoTask = toDoTask,
                onBackArrowClicked = {},
                onDeleteClicked = {},
                onConfirmClicked = {}
            )
        }
    }
}

@Composable
fun CreateTaskTopBar(
    onBackArrowClicked: () -> Unit,
    onConfirmClicked: (Action) -> Unit
) {
    val backgroundColor = MaterialTheme.colors.toolbarBackground
    val contentColor = MaterialTheme.colors.toolbarContent

    TopAppBar(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBackArrowClicked) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
                Text(
                    text = "New Task",
                    fontSize = 16.sp
                )
            }
        },
        actions = {
            ConfirmAction(onConfirmClicked)
        }
    )
}

@Composable
fun ConfirmAction(
    onConfirmClicked: (Action) -> Unit,
) {
    val contentColor = MaterialTheme.colors.toolbarContent
    IconButton(onClick = { onConfirmClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = null,
            tint = contentColor
        )
    }
}

@Composable
fun EditTaskTopBar(
    toDoTask: ToDoTask,
    onBackArrowClicked: () -> Unit,
    onConfirmClicked: (Action) -> Unit,
    onDeleteClicked: () -> Unit
) {
    val backgroundColor = MaterialTheme.colors.toolbarBackground
    val contentColor = MaterialTheme.colors.toolbarContent

    TopAppBar(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBackArrowClicked) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
            }
            Text(
                text = toDoTask.title,
                fontSize = 16.sp
            )
        },
        actions = {
            DeleteAction(onDeleteClicked)

            ConfirmAction(onConfirmClicked = onConfirmClicked)
        }
    )
}

@Composable
fun DeleteAction(onDeleteClicked: () -> Unit) {
    val contentColor = MaterialTheme.colors.toolbarContent
    IconButton(onClick = onDeleteClicked) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = null,
            tint = contentColor
        )
    }
}

@Preview
@Composable
private fun CreateTaskTopBarPreview() {
    CreateTaskTopBar({}, {})
}

@Preview
@Composable
private fun EditTaskTopBarPreview() {
    EditTaskTopBar(ToDoTask(0, "Task Title", "", Priority.NONE), {}, {}, {})
}