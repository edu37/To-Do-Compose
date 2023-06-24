package com.example.to_docompose.presentation.ui.components.toolbar

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.to_docompose.R
import com.example.to_docompose.data.models.Priority
import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.presentation.ui.theme.toolbarBackground
import com.example.to_docompose.presentation.ui.theme.toolbarContent
import com.example.to_docompose.presentation.viewmodel.task.TaskContract as Contract

@Composable
fun TaskScreenTopBar(
    toDoTask: ToDoTask?,
    onGoBackScreen: () -> Unit,
    onDeleteClicked: (ToDoTask) -> Unit,
    onConfirmClicked: (ToDoTask) -> Unit,
) {

    if (toDoTask == null) {
        CreateTaskTopBar(
            toDoTask = toDoTask,
            onBackArrowClicked = onGoBackScreen,
            onConfirmClicked = { task ->
                onConfirmClicked(task)
            }
        )
    } else {
        EditTaskTopBar(
            toDoTask = toDoTask,
            onCloseArrowClicked = onGoBackScreen,
            onDeleteClicked = { task ->
                onDeleteClicked(task)
            },
            onConfirmClicked = { task ->
                onConfirmClicked(task)
            }
        )

    }
}

@Composable
fun CreateTaskTopBar(
    toDoTask: ToDoTask?,
    onBackArrowClicked: () -> Unit,
    onConfirmClicked: (ToDoTask) -> Unit
) {
    val backgroundColor = MaterialTheme.colors.toolbarBackground
    val contentColor = MaterialTheme.colors.toolbarContent

    TopAppBar(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        navigationIcon = {
            IconButton(onClick = onBackArrowClicked) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            }
        },
        title = {
            Text(
                text = stringResource(id = R.string.new_task),
                fontSize = 16.sp
            )
        },
        actions = {
            ConfirmAction(toDoTask, onConfirmClicked)
        }
    )
}

@Composable
fun ConfirmAction(
    toDoTask: ToDoTask?,
    onConfirmClicked: (ToDoTask) -> Unit,
) {
    val contentColor = MaterialTheme.colors.toolbarContent
    IconButton(onClick = { toDoTask?.let(onConfirmClicked) }) {
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
    onCloseArrowClicked: () -> Unit,
    onConfirmClicked: (ToDoTask) -> Unit,
    onDeleteClicked: (ToDoTask) -> Unit
) {
    val backgroundColor = MaterialTheme.colors.toolbarBackground
    val contentColor = MaterialTheme.colors.toolbarContent

    TopAppBar(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        navigationIcon = {
            IconButton(onClick = onCloseArrowClicked) {
                Icon(imageVector = Icons.Filled.Close, contentDescription = null)
            }
        },
        title = {
            Text(
                text = toDoTask.title,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            DeleteAction(toDoTask, onDeleteClicked)

            ConfirmAction(toDoTask, onConfirmClicked)
        }
    )
}

@Composable
fun DeleteAction(
    toDoTask: ToDoTask,
    onDeleteClicked: (ToDoTask) -> Unit
) {
    val contentColor = MaterialTheme.colors.toolbarContent
    IconButton(onClick = { onDeleteClicked(toDoTask) }) {
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
    CreateTaskTopBar(
        Contract.State().toDoTask,
        {},
        {})
}

@Preview
@Composable
private fun EditTaskTopBarPreview() {
    EditTaskTopBar(ToDoTask(0, "Task Title", "", Priority.NONE), {}, {}, {})
}