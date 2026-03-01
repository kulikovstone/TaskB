package com.mkulikov.taskb.group.di

import com.mkulikov.taskb.core.database.TaskBDatabase
import com.mkulikov.taskb.core.navigation.INavEntry
import com.mkulikov.taskb.group.navigation.GroupNavEntry
import com.mkulikov.taskb.group.screen.add_group.AddGroupViewModel
import com.mkulikov.taskb.group.screen.edit_group.EditGroupViewModel
import com.mkulikov.taskb.group.screen.group_list.GroupListViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun groupModules() = listOf(common, navigation, database)

internal val common = module {
    viewModelOf(::GroupListViewModel)
    viewModelOf(::AddGroupViewModel)
    viewModelOf(::EditGroupViewModel)
}

internal val navigation = module {
    factoryOf(::GroupNavEntry) { bind<INavEntry>() }
}

internal val database = module {
    single { get<TaskBDatabase>().groupDao() }
}