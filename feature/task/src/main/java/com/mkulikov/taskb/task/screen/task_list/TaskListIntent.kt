package com.mkulikov.taskb.task.screen.task_list

sealed class TaskListIntent {

    data object OpenSettings : TaskListIntent()
    data object OpenFilters : TaskListIntent()
    data object ClearFilters : TaskListIntent()
    data object AddTask : TaskListIntent()
    data class OpenTask(val task: Task) : TaskListIntent()
    data class ChangeTaskCompletion(val task: Task) : TaskListIntent()
}