package com.example.to_docompose.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.to_docompose.data.models.ToDoTask
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTasksRealTime(): Flow<List<ToDoTask>>

    @Query("SELECT * FROM todo_table WHERE id=:taskId")
    fun getSelectedTask(taskId: Int): Flow<ToDoTask>

    @Query("SELECT * FROM todo_table WHERE description LIKE :searchQuery OR title LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<ToDoTask>>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE '$LOW%' THEN 1 WHEN priority LIKE '$MEDIUM%' THEN 2 WHEN priority LIKE '$HIGH%' THEN 3 END")
    fun sortByLowPriority(): Flow<List<ToDoTask>>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE '$HIGH%' THEN 1 WHEN priority LIKE '$MEDIUM%' THEN 2 WHEN priority LIKE '$LOW%' THEN 3 END")
    fun sortByHighPriority(): Flow<List<ToDoTask>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(toDoTask: ToDoTask)

    @Update
    suspend fun updateTask(toDoTask: ToDoTask)

    @Delete
    suspend fun deleteTask(toDoTask: ToDoTask)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAllTasks()

    private companion object {
        const val LOW = 'L'
        const val MEDIUM = 'M'
        const val HIGH = 'H'
    }
}