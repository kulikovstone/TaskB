package com.mkulikov.taskb.home_api.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface HomeRoute : NavKey {

    @Serializable
    data object Home : HomeRoute
}