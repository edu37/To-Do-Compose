package com.example.to_docompose.presentation.ui.screens.route.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.to_docompose.data.models.Priority
import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.presentation.ui.components.fab.ListScreenFloatingActionButton
import com.example.to_docompose.presentation.ui.components.item.TaskItem
import com.example.to_docompose.presentation.ui.components.toolbar.ListScreenTopBar
import com.example.to_docompose.presentation.viewmodel.list.ListContract

@Composable
fun ListScreen(
    state: ListContract.State,
    onFabClicked: (Int) -> Unit
) {

    Scaffold(
        topBar = {
            ListScreenTopBar()
        },
        floatingActionButton = {
            ListScreenFloatingActionButton(
                onFabClicked
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(it)
        ) {
            item {
                TaskItem(
                    toDoTask = ToDoTask(
                        0,
                        "Teste",
                        "This is a description text test thas is been used on Preview to see if this string is working the way it should. Ellipsis should be able to see now",
                        Priority.HIGH
                    )
                ) {}
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                TaskItem(
                    toDoTask = ToDoTask(
                        0,
                        "Teste",
                        "This is a description text test thas is been used on Preview to see if this string is working the way it should. Ellipsis should be able to see now",
                        Priority.HIGH
                    )
                ) {}
            }
        }
    }

}

@Preview
@Composable
fun ListScreenPreview() {
    ListScreen(
        ListContract.State(),
        onFabClicked = {}
    )
}