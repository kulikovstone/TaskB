package com.mkulikov.taskb.group.di

import com.mkulikov.taskb.core.database.TaskBDatabase
import com.mkulikov.taskb.core.navigation.INavEntry
import com.mkulikov.taskb.group.repository.GroupRepository
import com.mkulikov.taskb.group.interactor.mapper.GroupMapper
import com.mkulikov.taskb.group.holder.GroupEntityHolder
import com.mkulikov.taskb.group.interactor.GroupInteractor
import com.mkulikov.taskb.group.interactor.IGroupInteractor
import com.mkulikov.taskb.group.navigation.GroupNavEntry
import com.mkulikov.taskb.group.screen.add_group.AddGroupViewModel
import com.mkulikov.taskb.group.screen.edit_group.EditGroupViewModel
import com.mkulikov.taskb.group.screen.group_list.GroupListViewModel
import com.mkulikov.taskb.group_api.database.IGroupRepository
import com.mkulikov.taskb.group_api.holder.IGroupEntityHolder
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

fun groupModules() = listOf(common, navigation, database)

internal val common = module {
    viewModelOf(::GroupListViewModel)
    viewModelOf(::AddGroupViewModel)

    viewModel { params ->
        EditGroupViewModel(
            groupId = params.get(),
            appNavigator = get(),
            groupInteractor = get()
        )
    }

    singleOf(::GroupRepository).bind(IGroupRepository::class)
    singleOf(::GroupInteractor).bind(IGroupInteractor::class)
    singleOf(::GroupEntityHolder).bind(IGroupEntityHolder::class)
    singleOf(::GroupMapper)
}

internal val navigation = module {
    factoryOf(::GroupNavEntry) { bind<INavEntry>() }
}

internal val database = module {
    single { get<TaskBDatabase>().groupDao() }
}