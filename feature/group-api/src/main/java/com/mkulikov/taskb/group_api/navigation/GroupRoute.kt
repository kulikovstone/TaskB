package com.mkulikov.taskb.group_api.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface GroupRoute : NavKey {

    @Serializable
    data object GroupList : GroupRoute
}