package com.mkulikov.taskb.ui.screen.main

sealed class TaskFilter {

    data object Uncompleted : TaskFilter()
    data object Today : TaskFilter()
    data object WithoutGroup : TaskFilter()
    data object Completed : TaskFilter()
    data class Group(val id: Int) : TaskFilter()
}