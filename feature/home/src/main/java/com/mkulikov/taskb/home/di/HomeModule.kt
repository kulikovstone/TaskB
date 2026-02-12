package com.mkulikov.taskb.home.di

import com.mkulikov.taskb.di.FeatureModules
import com.mkulikov.taskb.home.screen.start.StartViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

object HomeModules : FeatureModules {

    override val modules = listOf(commonModule)
}

val commonModule = module {

    viewModelOf(::StartViewModel)
}