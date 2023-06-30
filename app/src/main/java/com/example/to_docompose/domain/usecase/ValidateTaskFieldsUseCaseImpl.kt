package com.example.to_docompose.domain.usecase

import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.util.extension.isValid

class ValidateTaskFieldsUseCaseImpl : ValidateTaskFieldsUseCase {

    override fun invoke(toDoTask: ToDoTask): Boolean {
        return with(toDoTask) {
            title.isValid() && description.isValid()
        }
    }
}