package com.mkulikov.taskb.task.ext

import com.mkulikov.taskb.task_api.database.TaskEntity
import com.mkulikov.taskb.base.now
import kotlinx.datetime.LocalDate

fun TaskEntity.isCompleted(): Boolean = completedAt != null

fun TaskEntity.isToday(): Boolean {
//    return !isCompleted() && targetDate != null && targetDate!! <= LocalDate.now()
    return false
}

fun TaskEntity.isWithoutGroup(): Boolean {
    return !isCompleted() && groupId == null
}

fun TaskEntity.isGroup(id: Int): Boolean {
    return !isCompleted() && groupId == id
}