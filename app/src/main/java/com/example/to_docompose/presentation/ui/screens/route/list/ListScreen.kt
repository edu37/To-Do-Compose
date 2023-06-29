package com.example.to_docompose.presentation.ui.screens.route.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.to_docompose.R
import com.example.to_docompose.data.models.Priority
import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.presentation.ui.components.fab.ListScreenFloatingActionButton
import com.example.to_docompose.presentation.ui.components.item.TaskItem
import com.example.to_docompose.presentation.ui.components.toolbar.ListScreenTopBar
import com.example.to_docompose.presentation.ui.theme.MediumGray
import com.example.to_docompose.presentation.viewmodel.list.ListContract as Contract

@Composable
fun ListScreen(
    taskList: List<ToDoTask>,
    isLoading: Boolean,
    sendEvent: (Contract.Event) -> Unit
) {

    Scaffold(
        topBar = {
            ListScreenTopBar(
                sendEvent = sendEvent
            )
        },
        floatingActionButton = {
            ListScreenFloatingActionButton(
                onFabClicked = { sendEvent(Contract.Event.OnFabClicked) }
            )
        }
    ) { scaffoldPadding ->
        if (!isLoading) {
            if (taskList.isEmpty()) {
                EmptyTaskList(scaffoldPadding)
            } else {
                TaskList(
                    taskList = taskList,
                    scaffoldPadding = scaffoldPadding,
                    onTaskClicked = { taskId ->
                        sendEvent(Contract.Event.OnTaskClicked(taskId))
                    }
                )
            }
        }
//        else {
//            Box(
//                modifier = Modifier.fillMaxSize(),
//                contentAlignment = Alignment.Center
//            ) {
//                CircularProgressIndicator(
//                    color = MediumGray,
//                    modifier = Modifier.size(50.dp)
//                )
//            }
//        }
    }
}

@Composable
fun TaskList(
    taskList: List<ToDoTask>,
    scaffoldPadding: PaddingValues,
    onTaskClicked: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(scaffoldPadding)
    ) {
        taskList.forEach { task ->
            item {
                TaskItem(
                    toDoTask = task,
                    onTaskClicked = onTaskClicked
                )
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
        }
    }
}

@Composable
fun EmptyTaskList(
    scaffoldPadding: PaddingValues
) {
    val contentColor = MediumGray
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(scaffoldPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_sad_face),
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier.size(120.dp)
        )
        Text(
            text = "No tasks found. Try create one by clicking down below",
            textAlign = TextAlign.Center,
            color = contentColor,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

@Preview
@Composable
fun ListScreenPreview() {
    ListScreen(
        taskList = listOf(
            ToDoTask(
                0,
                "Teste",
                "This is a description text test thas is been used on Preview to see if this string is working the way it should. Ellipsis should be able to see now",
                Priority.HIGH
            ), ToDoTask(
                0,
                "Teste",
                "This is a description text test thas is been used on Preview to see if this string is working the way it should. Ellipsis should be able to see now",
                Priority.HIGH
            )
        ),
        isLoading = false,
        sendEvent = {}
    )
}

@Preview
@Composable
fun EmptyListScreenPreview() {
    ListScreen(
        taskList = emptyList(),
        isLoading = false,
        sendEvent = {}
    )
}