package com.example.to_docompose.data.repository

import com.example.to_docompose.data.local.ToDoDao
import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.domain.repository.ToDoRepository
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(
    private val toDoDao: ToDoDao
) : ToDoRepository {

    override fun getSelectedTask(taskId: Int) = toDoDao.getSelectedTask(taskId)

    override fun getAllTasks() = toDoDao.getAllTasksRealTime()

    override fun searchDatabase(searchQuery: String) = toDoDao.searchDatabase("%$searchQuery%")

    override fun sortByLowPriority() = toDoDao.sortByLowPriority()

    override fun sortByHighPriority() = toDoDao.sortByHighPriority()

    override suspend fun addTask(toDoTask: ToDoTask) = toDoDao.addTask(toDoTask)

    override suspend fun updateTask(toDoTask: ToDoTask) = toDoDao.updateTask(toDoTask)

    override suspend fun deleteTask(toDoTask: ToDoTask) = toDoDao.deleteTask(toDoTask)

    override suspend fun deleteAllTasks() = toDoDao.deleteAllTasks()

}