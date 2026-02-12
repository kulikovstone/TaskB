package com.mkulikov.taskb.di

import org.koin.core.module.Module

interface FeatureModules {
    val modules: List<Module>
}

fun featureModules(vararg features: FeatureModules): List<Module> =
    features.asList().flatMap { it.modules }