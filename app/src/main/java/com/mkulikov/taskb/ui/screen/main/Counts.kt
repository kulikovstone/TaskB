package com.mkulikov.taskb.ui.screen.main

data class Counts(
    val uncompleted: Int,
    val today: Int,
    val withoutGroup: Int,
    val group: Map<Int, Int>,
)