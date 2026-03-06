package com.mkulikov.taskb.task_api.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val groupId: Int?,
    val description: String? = null,
    val createdAt: String? = null,
    val targetDate: String? = null,
    val completedAt: String? = null,
)