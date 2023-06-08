package com.example.to_docompose.ui.screens.components.fab

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ListScreenFloatingActionButton(
    onFabClicked: (Int) -> Unit
) {
    FloatingActionButton(onClick = { onFabClicked.invoke(-1) }) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Botão para adicionar tarefas",
            tint = Color.White
        )
    }
}