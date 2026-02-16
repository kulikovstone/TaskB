package com.mkulikov.taskb.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import com.mkulikov.taskb.core.database.TaskBDatabase.Companion.DB_NAME
import com.mkulikov.taskb.feature.task_data.database.Group
import com.mkulikov.taskb.feature.task_data.database.GroupDao
import com.mkulikov.taskb.feature.task_data.database.Task
import com.mkulikov.taskb.feature.task_data.database.TaskDao
import com.mkulikov.taskb.feature.task_data.database.converter.TaskConverter

@Database(
    entities = [
        Group::class, Task::class, // Task module
    ],
    version = TaskBDatabase.VERSION,
    exportSchema = true,
)
@TypeConverters(
    TaskConverter::class, // Task module
)
abstract class TaskBDatabase : RoomDatabase() {

    abstract fun groupDao(): GroupDao

    abstract fun taskDao(): TaskDao

    companion object {
        const val VERSION = 1
        const val DB_NAME = "taskb.db"
    }
}

internal fun createDatabase(
    context: Context,
    migrations: List<Migration>,
    callbacks: List<RoomDatabase.Callback>,
): TaskBDatabase {
    val builder = Room.databaseBuilder(context, TaskBDatabase::class.java, DB_NAME)

    if (migrations.isNotEmpty()) {
        builder.addMigrations(*migrations.toTypedArray())
    }

    callbacks.forEach(builder::addCallback)

    builder.fallbackToDestructiveMigrationOnDowngrade(true)
//    builder.fallbackToDestructiveMigration(true)

    return builder.build()
}