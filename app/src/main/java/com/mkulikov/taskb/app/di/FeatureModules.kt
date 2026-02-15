package com.mkulikov.taskb.app.di

import com.mkulikov.taskb.navigation.di.navigationModules
import com.mkulikov.taskb.home.di.homeModules
import com.mkulikov.taskb.task.di.taskModules
import org.koin.core.module.Module

internal fun featureModules(): List<Module> = listOf(
    appModules(),
    navigationModules(),
    homeModules(),
    taskModules(),
).flatten()