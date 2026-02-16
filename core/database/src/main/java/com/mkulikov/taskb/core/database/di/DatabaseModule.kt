package com.mkulikov.taskb.core.database.di

import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.mkulikov.taskb.core.database.TaskBDatabase
import com.mkulikov.taskb.core.database.createDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun databaseModules() = listOf(common)

internal val common = module {

    single<TaskBDatabase> {
        createDatabase(
            context = androidContext(),
            migrations = getKoin().getAll<Migration>(),
            callbacks = getKoin().getAll<RoomDatabase.Callback>(),
        )
    }
}