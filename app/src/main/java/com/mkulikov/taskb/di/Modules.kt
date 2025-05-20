package com.mkulikov.taskb.di

import com.mkulikov.taskb.data.IMainRepository
import com.mkulikov.taskb.data.ITestNetworkRepository
import com.mkulikov.taskb.data.MainRepository
import com.mkulikov.taskb.data.TestNetworkRepository
import com.mkulikov.taskb.database.AppDatabase
import com.mkulikov.taskb.database.getAppDatabase
import com.mkulikov.taskb.holder.GroupDataHolder
import com.mkulikov.taskb.holder.IGroupDataHolder
import com.mkulikov.taskb.holder.ITaskDataHolder
import com.mkulikov.taskb.holder.TaskDataHolder
import com.mkulikov.taskb.network.TestApiService
import com.mkulikov.taskb.network.KtorClient
import com.mkulikov.taskb.ui.screen.add_group.AddGroupViewModel
import com.mkulikov.taskb.ui.screen.edit_group.EditGroupViewModel
import com.mkulikov.taskb.ui.screen.main.MainViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

// todo Разбить на отдельные тематические модули
val commonModule = module {
    single { KtorClient.httpClient }
    single { TestApiService(get()) }
    singleOf(::TestNetworkRepository).bind(ITestNetworkRepository::class)

    single<AppDatabase> { getAppDatabase(get()) }
    single { get<AppDatabase>().groupDao() }
    single { get<AppDatabase>().taskDao() }
    singleOf(::MainRepository).bind(IMainRepository::class)

    singleOf(::GroupDataHolder).bind(IGroupDataHolder::class)
    singleOf(::TaskDataHolder).bind(ITaskDataHolder::class)

    viewModelOf(::MainViewModel)
    viewModelOf(::AddGroupViewModel)
    viewModelOf(::EditGroupViewModel)
}