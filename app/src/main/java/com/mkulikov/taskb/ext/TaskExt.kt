package com.mkulikov.taskb.ext

import com.mkulikov.taskb.database.Task
import kotlinx.datetime.LocalDate

fun Task.isCompleted(): Boolean = completedAt != null

fun Task.isToday(): Boolean {
    return !isCompleted() && targetDate != null && targetDate <= LocalDate.now()
}

fun Task.isWithoutGroup(): Boolean {
    return !isCompleted() && groupId == null
}

fun Task.isGroup(id: Int): Boolean {
    return !isCompleted() && groupId == id
}