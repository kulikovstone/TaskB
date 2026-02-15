package com.mkulikov.taskb.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mkulikov.taskb.task.data.Group
import com.mkulikov.taskb.task.data.GroupDao
import com.mkulikov.taskb.task.data.Task
import com.mkulikov.taskb.task.data.TaskDao

@Database(
    entities = [Group::class, Task::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun groupDao(): GroupDao

    abstract fun taskDao(): TaskDao
}

fun getAppDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context,
        AppDatabase::class.java, "taskb-database"
    )
        .fallbackToDestructiveMigration(true)
        .build()
}