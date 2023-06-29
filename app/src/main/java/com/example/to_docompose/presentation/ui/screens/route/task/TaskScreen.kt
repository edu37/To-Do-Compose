package com.example.to_docompose.presentation.ui.screens.route.task

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ContentAlpha
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.to_docompose.data.models.Priority
import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.presentation.ui.components.item.PriorityItem
import com.example.to_docompose.presentation.ui.components.toolbar.TaskScreenTopBar
import com.example.to_docompose.util.TestTags
import com.example.to_docompose.presentation.viewmodel.task.TaskContract as Contract

const val EMPTY_STRING = ""

@Composable
fun TaskScreen(
    toDoTask: ToDoTask?,
    sendEvent: (Contract.Event) -> Unit
) {

    var priority by rememberSaveable { mutableStateOf(Priority.LOW) }
    var title by rememberSaveable { mutableStateOf(EMPTY_STRING) }
    var description by rememberSaveable { mutableStateOf(EMPTY_STRING) }

    if (toDoTask != null) {
        title = toDoTask.title
        priority = toDoTask.priority
        description = toDoTask.description
    }

    Scaffold(
        topBar = {
            TaskScreenTopBar(
                toDoTask = toDoTask,
                onGoBackScreen = { sendEvent(Contract.Event.GoBackScreen) },
                onDeleteClicked = { task ->
                    sendEvent(Contract.Event.DeleteTask(task))
                },
                onCreateTask = {
                    sendEvent(
                        Contract.Event.AddTask(
                            ToDoTask(
                                title = title,
                                description = description,
                                priority = priority
                            )
                        )
                    )
                },
                onUpdateClicked = {
                    sendEvent(
                        Contract.Event.UpdateTask(
                            ToDoTask(
                                id = toDoTask!!.id,
                                title = title,
                                description = description,
                                priority = priority
                            )
                        )
                    )
                },
            )
        },
        modifier = Modifier.testTag(TestTags.TASK_SCREEN)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(it)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                onValueChange = { input ->
                    title = input
                },
                label = {
                    Text(text = "Title")
                },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            PriorityDropDown(
                priority = priority,
                onPrioritySelected = { prioritySelected ->
                    priority = prioritySelected
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxSize(),
                value = description,
                onValueChange = { input ->
                    description = input
                },
                label = {
                    Text(text = "Description")
                }
            )
        }

    }

}

@Composable
fun PriorityDropDown(
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val arrowAngle by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f
    )

    fun dismissMenuItem() {
        expanded = false
    }

    fun popUpMenuItem() {
        expanded = true
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val width = this.maxWidth
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clickable { popUpMenuItem() }
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.onSurface.copy(ContentAlpha.disabled)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Canvas(
                modifier = Modifier
                    .size(16.dp)
                    .weight(1f)
            ) {
                drawCircle(priority.color)
            }
            Text(
                text = priority.name,
                modifier = Modifier.weight(7f)
            )
            IconButton(
                onClick = { popUpMenuItem() },
                modifier = Modifier
                    .weight(2f)
                    .alpha(ContentAlpha.medium)
                    .rotate(arrowAngle)
            ) {
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { dismissMenuItem() },
                modifier = Modifier
                    .width(width)
            ) {
                DropdownMenuItem(onClick = {
                    dismissMenuItem()
                    onPrioritySelected(Priority.LOW)
                }) {
                    PriorityItem(priority = Priority.LOW)
                }
                DropdownMenuItem(onClick = {
                    dismissMenuItem()
                    onPrioritySelected(Priority.MEDIUM)
                }) {
                    PriorityItem(priority = Priority.MEDIUM)
                }
                DropdownMenuItem(onClick = {
                    dismissMenuItem()
                    onPrioritySelected(Priority.HIGH)
                }) {
                    PriorityItem(priority = Priority.HIGH)
                }
            }
        }
    }
}

@Preview
@Composable
fun TaskScreenPreview() {
    TaskScreen(
        toDoTask = ToDoTask(
            0,
            "Paintball",
            "Jogar umas partidas de paintball com os fela",
            Priority.HIGH
        ),
        sendEvent = {}
    )
}