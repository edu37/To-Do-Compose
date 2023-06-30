package com.example.to_docompose.domain.usecase

import com.example.to_docompose.data.models.ToDoTask

interface ValidateTaskFieldsUseCase {

    operator fun invoke(toDoTask: ToDoTask): Boolean
}