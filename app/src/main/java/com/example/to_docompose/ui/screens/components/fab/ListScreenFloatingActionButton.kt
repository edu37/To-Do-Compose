package com.example.to_docompose.ui.screens.components.fab

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable

@Composable
fun ListScreenFloatingActionButton() {
    FloatingActionButton(onClick = { /*TODO*/ }) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Botão para adicionar tarefas"
        )
    }
}