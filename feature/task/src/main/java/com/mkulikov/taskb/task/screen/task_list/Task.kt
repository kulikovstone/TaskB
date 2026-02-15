package com.mkulikov.taskb.task.screen.task_list

import androidx.compose.ui.graphics.Color

data class Task(
    val id: Int,
    val name: String,
    val color: Color? = null,
    val isCompleted: Boolean,
)