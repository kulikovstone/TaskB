package com.mkulikov.taskb.task.screen.task_list

sealed class TaskListState {

    data object Loading : TaskListState()
    data class Data(
        val title: String,
        val filtered: Boolean,
        val tasks: List<Task>,
    ) : TaskListState()
}