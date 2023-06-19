package com.example.to_docompose.data.models

import androidx.compose.ui.graphics.Color
import com.example.to_docompose.presentation.ui.theme.HighPriorityColor
import com.example.to_docompose.presentation.ui.theme.LowPriorityColor
import com.example.to_docompose.presentation.ui.theme.MediumGray
import com.example.to_docompose.presentation.ui.theme.MediumPriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(MediumGray)
}