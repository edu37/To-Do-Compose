package com.example.to_docompose.presentation.ui.components.fab

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import com.example.to_docompose.presentation.ui.theme.fabBackground
import com.example.to_docompose.util.TestTags

@Composable
fun ListScreenFloatingActionButton(
    onFabClicked: () -> Unit
) {
    FloatingActionButton(
        onClick = { onFabClicked.invoke() },
        backgroundColor = MaterialTheme.colors.fabBackground,
        modifier = Modifier.testTag(TestTags.FAB_BUTTON)
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Botão para adicionar tarefas",
            tint = Color.White
        )
    }
}