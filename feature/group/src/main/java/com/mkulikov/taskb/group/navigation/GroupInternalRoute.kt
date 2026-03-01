package com.mkulikov.taskb.group.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface GroupInternalRoute : NavKey {

    @Serializable
    data object AddGroup : GroupInternalRoute

    @Serializable
    data class EditGroup(val groupId: Int) : GroupInternalRoute
}