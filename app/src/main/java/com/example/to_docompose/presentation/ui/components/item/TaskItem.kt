package com.example.to_docompose.presentation.ui.components.item

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.to_docompose.data.models.Priority
import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.presentation.ui.theme.MediumGray
import com.example.to_docompose.presentation.ui.theme.taskTitle

@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    onTaskClicked: (Int) -> Unit
) {
    val subtitleColor = MediumGray
    val titleColor = MaterialTheme.colors.taskTitle

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .padding(8.dp)
            .clickable { onTaskClicked(toDoTask.id) }
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = toDoTask.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = titleColor
                )
                Canvas(modifier = Modifier.size(16.dp)) {
                    drawCircle(color = toDoTask.priority.color)
                }
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = toDoTask.description,
                fontSize = 14.sp,
                color = subtitleColor,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                maxLines = 2
            )
        }
    }
}

@Preview
@Composable
fun TaskItemPreview() {
    TaskItem(
        toDoTask = ToDoTask(
            0,
            "Teste",
            "This is a description text test thas is been used on Preview to see if this string is working the way it should. Ellipsis should be able to see now",
            Priority.HIGH
        ),
        onTaskClicked = {}
    )
}