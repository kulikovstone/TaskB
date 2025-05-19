package com.mkulikov.taskb.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mkulikov.taskb.ext.now
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val groupId: Int?,
    val description: String? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val targetDate: LocalDate? = null,
    val completedAt: LocalDateTime? = null,
)