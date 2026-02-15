package com.mkulikov.taskb.task.screen_old.main

data class Counts(
    val uncompleted: Int,
    val today: Int,
    val withoutGroup: Int,
    val group: Map<Int, Int>,
)