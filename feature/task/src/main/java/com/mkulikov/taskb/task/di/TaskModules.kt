package com.mkulikov.taskb.task.di

import com.mkulikov.taskb.core.database.TaskBDatabase
import com.mkulikov.taskb.core.navigation.INavEntry
import com.mkulikov.taskb.task.holder.ITaskDataHolder
import com.mkulikov.taskb.task.holder.TaskDataHolder
import com.mkulikov.taskb.task.navigation.TaskNavEntry
import com.mkulikov.taskb.task.repository.IMainRepository
import com.mkulikov.taskb.task.repository.MainRepository
import com.mkulikov.taskb.task.screen.task_list.TaskListViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

fun taskModules() = listOf(common, navigation, database)

internal val common = module {

    singleOf(::MainRepository).bind(IMainRepository::class)
    singleOf(::TaskDataHolder).bind(ITaskDataHolder::class)

    viewModelOf(::TaskListViewModel)
}

internal val navigation = module {
    factoryOf(::TaskNavEntry) { bind<INavEntry>() }
}

internal val database = module {
    single { get<TaskBDatabase>().taskDao() }

//    single<Migration> { TaskMigration.MIGRATION_1_2 }
}