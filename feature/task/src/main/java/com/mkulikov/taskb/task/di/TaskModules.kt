package com.mkulikov.taskb.task.di

import com.mkulikov.taskb.navigation.INavEntry
import com.mkulikov.taskb.task.navigation.TaskNavEntry
import com.mkulikov.taskb.task.screen.task_list.TaskListViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun taskModules() = listOf(commonModule, navigationModule)

internal val commonModule = module {

    viewModelOf(::TaskListViewModel)
}

internal val navigationModule = module {
    factoryOf(::TaskNavEntry) { bind<INavEntry>() }
}