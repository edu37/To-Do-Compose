package com.example.to_docompose.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LightGray = Color(0xFFFCFCFC)
val MediumGray = Color(0xFF9C9C9C)
val DarkGray = Color(0xFF1C1C1C)


val LowPriorityColor = Color(0xFF77D55C)
val MediumPriorityColor = Color(0xFFE6772D)
val HighPriorityColor = Color(0xFFEB4C46)
val NonePriorityColor = Color(0xFFFFFFFF)

val Colors.toolbarContent: Color
    @Composable
    get() = if (isLight) Color.White else LightGray

val Colors.toolbarBackground: Color
    @Composable
    get() = if (isLight) MaterialTheme.colors.primary else DarkGray