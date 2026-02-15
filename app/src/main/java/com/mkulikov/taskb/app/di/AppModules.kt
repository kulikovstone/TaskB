package com.mkulikov.taskb.app.di

import com.mkulikov.taskb.app.database.AppDatabase
import com.mkulikov.taskb.app.database.getAppDatabase
import com.mkulikov.taskb.task.holder.GroupDataHolder
import com.mkulikov.taskb.task.holder.IGroupDataHolder
import com.mkulikov.taskb.task.holder.ITaskDataHolder
import com.mkulikov.taskb.task.holder.TaskDataHolder
import com.mkulikov.taskb.task.repository.IMainRepository
import com.mkulikov.taskb.task.repository.MainRepository
import com.mkulikov.taskb.task.screen.task_list.TaskListViewModel
import com.mkulikov.taskb.task.screen_old.add_group.AddGroupViewModel
import com.mkulikov.taskb.task.screen_old.auth.login.LoginViewModel
import com.mkulikov.taskb.task.screen_old.edit_group.EditGroupViewModel
import com.mkulikov.taskb.task.screen_old.main.TaskViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

fun appModules() = listOf(commonModule)

val commonModule = module {
//    single { KtorClient.httpClient }
//    single { TestApiService(get()) }
//    singleOf(::TestNetworkRepository).bind(ITestNetworkRepository::class)

    single<AppDatabase> { getAppDatabase(get()) }
    single { get<AppDatabase>().groupDao() }
    single { get<AppDatabase>().taskDao() }
    singleOf(::MainRepository).bind(IMainRepository::class)

    singleOf(::GroupDataHolder).bind(IGroupDataHolder::class)
    singleOf(::TaskDataHolder).bind(ITaskDataHolder::class)

    viewModelOf(::LoginViewModel)
    viewModelOf(::TaskViewModel)
    viewModelOf(::TaskListViewModel)
    viewModelOf(::AddGroupViewModel)
    viewModelOf(::EditGroupViewModel)
}