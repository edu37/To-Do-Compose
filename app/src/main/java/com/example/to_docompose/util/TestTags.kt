package com.example.to_docompose.util

object TestTags {

    const val EMPTY_LIST = "EmptyListLayout"
    const val TASK_LIST = "TaskListLayout"
    const val MORE_OPTIONS_TOOLBAR = "MoreOptionsToolbar"
    const val DELETE_ALL_MORE_OPTION = "DeleteAllTasksOption"
    const val DELETE_TASK_TOOLBAR_OPTION = "DeleteTaskToolbarOption"
    fun taskItem(taskId: Int) = "TaskItem_$taskId"
    const val TASK_SCREEN = "TaskScreen"
    const val LIST_SCREEN = "ListScreen"
    const val ARROW_BACK = "ArrowBack"
}