package com.mkulikov.taskb.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.mkulikov.taskb.core.database.TaskBDatabase.Companion.DB_NAME
import com.mkulikov.taskb.group_api.database.GroupDao
import com.mkulikov.taskb.group_api.database.GroupEntity
import com.mkulikov.taskb.task_api.database.TaskEntity
import com.mkulikov.taskb.task_api.database.TaskDao

@Database(
    entities = [
        GroupEntity::class,
        TaskEntity::class,
    ],
    version = TaskBDatabase.VERSION,
    exportSchema = true,
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