package com.mkulikov.taskb.core.di

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey
import com.mkulikov.taskb.core.navigation.AppNavigator
import com.mkulikov.taskb.core.navigation.IAppNavigator
import org.koin.dsl.module

fun navigationModules() = listOf(commonModule)

internal val commonModule = module {
    single<SnapshotStateList<NavKey>> { mutableStateListOf() }
    single<IAppNavigator> { AppNavigator(get()) }
}