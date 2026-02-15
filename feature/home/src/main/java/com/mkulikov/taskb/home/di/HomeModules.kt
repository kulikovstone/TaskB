package com.mkulikov.taskb.home.di

import com.mkulikov.taskb.core.navigation.INavEntry
import com.mkulikov.taskb.home.navigation.HomeNavEntry
import com.mkulikov.taskb.home.screen.start.HomeViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun homeModules() = listOf(commonModule, navigationModule)

internal val commonModule = module {

    viewModelOf(::HomeViewModel)
}

internal val navigationModule = module {

    factoryOf(::HomeNavEntry) { bind<INavEntry>() }
}